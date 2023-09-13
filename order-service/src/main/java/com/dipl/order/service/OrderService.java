package com.dipl.order.service;

import com.dipl.order.api.dto.CreateOrderRequest;
import com.dipl.order.api.dto.OrderItemDto;
import com.dipl.order.enums.OrderStatus;
import com.dipl.order.mapper.OrderMapper;
import com.dipl.order.message.MessageQueue;
import com.dipl.order.message.dto.CreatePaymentEvent;
import com.dipl.order.model.Item;
import com.dipl.order.model.Order;
import com.dipl.order.repo.OrderRepo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.spring.annotations.Recurring;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

  private final OrderMapper orderMapper;
  private final InventoryService inventoryService;
  private final PaymentService paymentService;
  private final MessageQueue messageQueue;
  private final OrderRepo repo;

  @Transactional
  public Order createOrder(CreateOrderRequest request) {
    log.info("Creating order for user with id '{}'", request.customerId());
    var availableItems =
        inventoryService.getItems(request.items().stream().map(OrderItemDto::id).toList());

    validateItems(request.items(), availableItems);

    var orderEntity = orderMapper.toEntity(request);
    orderEntity.addItems(request.items(), availableItems);
    orderEntity.setStatus(OrderStatus.PAID);

    var order = orderMapper.fromEntity(repo.save(orderEntity));

    inventoryService.decreaseQuantity(orderEntity.getId(), request.items());

    //Initiate payment
    paymentService.createPayment(order.getId(), request.customerId(), order.calculateTotal());

    log.info("Created order with id '{}'", order.getId());
    return order;
  }

  @Transactional
  public Order createOrderAsync(CreateOrderRequest request) {
    log.info("Creating async order for user with id '{}'", request.customerId());
    var availableItems =
        inventoryService.getItems(request.items().stream().map(OrderItemDto::id).toList());

    validateItems(request.items(), availableItems);

    var orderEntity = orderMapper.toEntity(request);
    orderEntity.addItems(request.items(), availableItems);
    orderEntity.setStatus(OrderStatus.PROCESSING);

    var persistedOrderEntity = repo.save(orderEntity);

    //inventoryService.decreaseQuantity(request.items());
    var order = orderMapper.fromEntity(persistedOrderEntity);
    messageQueue.send(orderMapper.toOrderCreatedEvent(order));

    log.info("Created async order with id '{}'", order.getId());
    return order;
  }

  public void validateItems(List<OrderItemDto> orderItems, List<Item> availableItems) {
    orderItems.forEach(orderItem ->
        availableItems.forEach(availableItem -> {
          if (availableItem.getId().equals(orderItem.id()) &&
              orderItem.quantity() > availableItem.getQuantity()) {

            throw new RuntimeException(
                "There is not enough " + availableItem.getName() + " quantity available." +
                    " Available quantity is: " + availableItem.getQuantity());
          }
        }));
  }

  @Transactional
  public void initiatePayment(Long orderId) {
    log.info("Initiating async payment for orderId '{}'", orderId);
    var orderEntity = repo.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Not found"));

    orderEntity.setStatus(OrderStatus.AWAITING_PAYMENT);

    var order = orderMapper.fromEntity(orderEntity);

    messageQueue.send(
        new CreatePaymentEvent(order.getId(), order.getCustomerId(), order.calculateTotal()));
  }

  @Transactional
  public void completeOrder(Long orderId) {
    log.info("Completing async orderId '{}'", orderId);

    var orderEntity = repo.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Not found"));

    orderEntity.setStatus(OrderStatus.PAID);
  }

  @Transactional
  @Job(name = "Retry payments")
  @Recurring(id = "retry-payments", cron = "*/10 * * * *")
  public void retryPayment() {
    var orders = repo.findByStatus(OrderStatus.AWAITING_PAYMENT);

    orders.forEach(orderEntity -> {
      var order = orderMapper.fromEntity(orderEntity);

      try {
        paymentService.createPayment(order.getId(), order.getCustomerId(), order.calculateTotal());
        orderEntity.setStatus(OrderStatus.PAID);
      } catch (Exception ex) {
        orderEntity.setStatus(OrderStatus.CANCELED);
      }
    });

    repo.saveAll(orders);
  }

}

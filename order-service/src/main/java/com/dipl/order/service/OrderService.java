package com.dipl.order.service;

import com.dipl.order.api.dto.CreateOrderRequest;
import com.dipl.order.mapper.OrderMapper;
import com.dipl.order.message.MessageQueue;
import com.dipl.order.model.Order;
import com.dipl.order.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

  private final OrderMapper orderMapper;
  private final MessageQueue messageQueue;
  private final OrderRepo repo;

  @Transactional
  public Order createOrder(CreateOrderRequest request) {

    //call inventory service
    //check the quantity and get the price

    var orderEntity = orderMapper.toEntity(request);
    orderEntity.addItems(request.items());

    var persistedOrderEntity = repo.save(orderEntity);

    var order = orderMapper.fromEntity(persistedOrderEntity);
    messageQueue.send(orderMapper.toOrderCreatedEvent(order));

    return orderMapper.fromEntity(persistedOrderEntity);
  }
}

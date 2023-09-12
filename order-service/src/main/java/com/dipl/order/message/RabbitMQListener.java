package com.dipl.order.message;

import com.dipl.order.message.dto.InventoryUpdatedEvent;
import com.dipl.order.message.dto.PaymentCompletedEvent;
import com.dipl.order.service.OrderService;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.dipl.order.message.RabbitMQConfiguration.INVENTORY_UPDATED_QUEUE;
import static com.dipl.order.message.RabbitMQConfiguration.PAYMENT_COMPLETED_QUEUE;

@Component
public class RabbitMQListener {

  private final OrderService orderService;
  private final Executor executorOne;
  private final Executor executorTwo;

  public RabbitMQListener(OrderService orderService,
      @Qualifier("internalTaskExecutorOne") Executor executorOne,
      @Qualifier("internalTaskExecutorTwo") Executor executorTwo) {
    this.orderService = orderService;
    this.executorOne = executorOne;
    this.executorTwo = executorTwo;
  }

  @RabbitListener(queues = INVENTORY_UPDATED_QUEUE)
  public void receive(@Payload InventoryUpdatedEvent event) {
    CompletableFuture.runAsync(() -> orderService.initiatePayment(event.orderId()), executorOne);
  }

  @RabbitListener(queues = PAYMENT_COMPLETED_QUEUE)
  public void receive(@Payload PaymentCompletedEvent event) {
    CompletableFuture.runAsync(() -> orderService.completeOrder(event.orderId()), executorTwo);
  }
}

package com.dipl.inventory.message;

import com.dipl.inventory.mapper.EventMapper;
import com.dipl.inventory.message.dto.InventoryUpdatedEvent;
import com.dipl.inventory.message.dto.OrderCreatedEvent;
import com.dipl.inventory.service.InventoryService;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQListener {

  public static final String ORDER_CREATED_QUEUE = "order-created-queue";
  public static final String INVENTORY_UPDATED_QUEUE = "inventory-updated-queue";

  private final InventoryService inventoryService;
  private final RabbitTemplate rabbitTemplate;
  private final EventMapper eventMapper;
  private final Executor executor;

  public RabbitMQListener(InventoryService inventoryService, RabbitTemplate rabbitTemplate,
      EventMapper eventMapper,
      @Qualifier("internalTaskExecutor") Executor executor) {
    this.inventoryService = inventoryService;
    this.rabbitTemplate = rabbitTemplate;
    this.eventMapper = eventMapper;
    this.executor = executor;
  }

  @RabbitListener(queues = ORDER_CREATED_QUEUE)
  public void receive(@Payload OrderCreatedEvent event) {

    CompletableFuture.runAsync(() -> inventoryService.decrease(eventMapper.fromEvent(event)),
            executor)
        .thenRun(() -> rabbitTemplate.convertAndSend(INVENTORY_UPDATED_QUEUE,
            new InventoryUpdatedEvent(event.orderId())));
  }
}

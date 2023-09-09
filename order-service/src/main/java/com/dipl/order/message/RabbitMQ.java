package com.dipl.order.message;

import com.dipl.order.message.dto.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static com.dipl.order.message.RabbitMQConfiguration.ORDER_CREATED_QUEUE;

@Component
@RequiredArgsConstructor
public class RabbitMQ implements MessageQueue {

  private final RabbitTemplate rabbitTemplate;

  @Override
  public void send(OrderCreatedEvent event) {
    rabbitTemplate.convertAndSend(ORDER_CREATED_QUEUE, event);
  }
}

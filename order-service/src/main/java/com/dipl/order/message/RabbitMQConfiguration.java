package com.dipl.order.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

  public static final String ORDER_CREATED_QUEUE = "order-created-queue";
  public static final String INVENTORY_UPDATED_QUEUE = "inventory-updated-queue";
  public static final String CREATE_PAYMENT_QUEUE = "create-payment-queue";
  public static final String PAYMENT_COMPLETED_QUEUE = "payment-completed-queue";

  @Bean
  public RabbitTemplate rabbitTemplate(
      ConnectionFactory connectionFactory, MessageConverter messageConverter) {

    var rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(messageConverter);

    return rabbitTemplate;
  }

  @Bean
  public MessageConverter messageConverter(ObjectMapper jsonMapper) {
    return new Jackson2JsonMessageConverter(jsonMapper);
  }
}

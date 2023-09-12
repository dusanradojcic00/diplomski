package com.dipl.order.message;

import com.dipl.order.message.dto.CreatePaymentEvent;
import com.dipl.order.message.dto.OrderCreatedEvent;

public interface MessageQueue {

  void send(OrderCreatedEvent message);

  void send(CreatePaymentEvent createPaymentEvent);
}

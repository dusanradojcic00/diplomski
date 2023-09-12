package com.dipl.inventory.message.dto;

import java.util.List;

public record OrderCreatedEvent(
    Long orderId,
    Long customerId,
    List<OrderItem> items
) {

  public record OrderItem(
      Long orderId,
      Long itemId,
      Integer quantity,
      Double pricePerUnit,
      Double totalPrice
  ) {

  }
}
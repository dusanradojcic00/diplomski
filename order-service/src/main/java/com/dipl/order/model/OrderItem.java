package com.dipl.order.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItem {

  private Long orderId;
  private Long itemId;
  private Integer quantity;
}

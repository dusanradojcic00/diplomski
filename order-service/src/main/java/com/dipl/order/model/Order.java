package com.dipl.order.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Order {

  private Long id;

  private Long customerId;

  private List<OrderItem> items;

  public Double calculateTotal() {

    return items.stream()
        .map(OrderItem::getTotalPrice)
        .reduce(0.0, Double::sum);
  }
}

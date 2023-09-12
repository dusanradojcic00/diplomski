package com.dipl.inventory.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Item {

  private Long id;
  private String name;
  private Integer quantity;
  private Double price;

  public void decrease(Integer quantity) {
    this.quantity = this.quantity - quantity;
  }
}

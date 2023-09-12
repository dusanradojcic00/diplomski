package com.dipl.order.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Item {

  private Long id;
  private String name;
  private Integer quantity;
  private Double price;
}

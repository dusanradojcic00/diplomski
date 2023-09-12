package com.dipl.inventory.api.dto;

import java.util.List;

public record DecreaseInventoryRequest(
    List<DecreaseItemRequest> items
) {
  public record DecreaseItemRequest(
      Long id,
      Integer quantity
  ){

  }
}

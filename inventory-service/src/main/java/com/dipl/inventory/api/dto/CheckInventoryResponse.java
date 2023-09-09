package com.dipl.inventory.api.dto;

public record CheckInventoryResponse(
    boolean allItemsAvailable
) {
}

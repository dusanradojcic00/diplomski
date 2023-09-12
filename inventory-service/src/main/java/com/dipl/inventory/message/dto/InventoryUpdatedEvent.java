package com.dipl.inventory.message.dto;

public record InventoryUpdatedEvent(
    Long orderId
) {
}

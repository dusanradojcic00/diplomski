package com.dipl.order.message.dto;

public record InventoryUpdatedEvent(
    Long orderId
) {
}

package com.dipl.order.message.dto;

public record PaymentCompletedEvent(
    Long orderId
) {
}

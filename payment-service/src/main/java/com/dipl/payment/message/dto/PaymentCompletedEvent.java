package com.dipl.payment.message.dto;

public record PaymentCompletedEvent(
    Long orderId
) {
}

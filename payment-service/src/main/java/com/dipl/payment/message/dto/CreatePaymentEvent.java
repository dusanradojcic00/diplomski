package com.dipl.payment.message.dto;

public record CreatePaymentEvent(
    Long orderId,
    Long customerId,
    Double total
) {
}
package com.dipl.order.message.dto;

public record CreatePaymentEvent(
    Long orderId,
    Long customerId,
    Double total
) {
}

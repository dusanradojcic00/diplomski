package com.dipl.payment.api.dto;

public record CreatePaymentRequest(
    Long userId,
    Double price
) {
}

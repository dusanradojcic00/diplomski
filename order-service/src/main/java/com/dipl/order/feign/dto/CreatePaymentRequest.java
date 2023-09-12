package com.dipl.order.feign.dto;

public record CreatePaymentRequest(
    Long userId,
    Double price
) {
}

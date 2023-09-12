package com.dipl.order.feign.dto;

public record ItemDto(
    Long id,
    String name,
    Integer quantity,
    Double price
) {
}


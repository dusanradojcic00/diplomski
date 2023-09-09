package com.dipl.inventory.api.dto;

public record ItemDto(
    Long id,
    Integer quantityLeft,
    Double price
) {
}

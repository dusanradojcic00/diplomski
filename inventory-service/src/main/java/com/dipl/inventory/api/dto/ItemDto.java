package com.dipl.inventory.api.dto;

public record ItemDto(
    Long id,
    String name,
    Integer quantity,
    Double price
) {
}

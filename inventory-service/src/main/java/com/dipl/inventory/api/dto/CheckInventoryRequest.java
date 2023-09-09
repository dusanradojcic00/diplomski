package com.dipl.inventory.api.dto;

import java.util.List;

public record CheckInventoryRequest(
    List<ItemDto> items
) {
}

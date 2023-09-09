package com.dipl.order.api.dto;


import java.util.List;

public record CreateOrderResponse(
    Long id,
    Long customerId,
    List<OrderItemDto> items
) {
}

package com.dipl.order.api.dto;

import java.util.List;

public record CreateOrderRequest(
    Long customerId,
    List<OrderItemDto> items
) {

}

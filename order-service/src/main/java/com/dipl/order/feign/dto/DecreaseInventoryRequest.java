package com.dipl.order.feign.dto;

import com.dipl.order.api.dto.OrderItemDto;
import java.util.List;

public record DecreaseInventoryRequest(
    Long orderId,
    List<OrderItemDto> items
) {

}


package com.dipl.order.message.dto;

import com.dipl.order.model.OrderItem;
import java.util.List;

public record OrderCreatedEvent(
    Long orderId,
    Long customerId,
    List<OrderItem> items
) {
}

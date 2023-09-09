package com.dipl.order.api.dto;

import com.dipl.order.model.Order;
import com.dipl.order.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderDtoMapper {

  CreateOrderResponse toResponse(Order order);

  @Mapping(target = "id", source = "itemId")
  OrderItemDto toDto(OrderItem orderItem);
}

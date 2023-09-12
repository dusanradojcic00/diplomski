package com.dipl.order.mapper;

import com.dipl.order.api.dto.CreateOrderRequest;
import com.dipl.order.message.dto.OrderCreatedEvent;
import com.dipl.order.model.Order;
import com.dipl.order.model.OrderItem;
import com.dipl.order.repo.entity.OrderEntity;
import com.dipl.order.repo.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderMapper {

  @Mapping(target = "items", ignore = true)
  OrderEntity toEntity(CreateOrderRequest request);

  Order fromEntity(OrderEntity orderEntity);

  @Mapping(target = "orderId", source = "id.orderId")
  @Mapping(target = "itemId", source = "id.itemId")
  OrderItem fromEntity(OrderItemEntity orderItemEntity);

  @Mapping(target = "orderId", source = "id")
  OrderCreatedEvent toOrderCreatedEvent(Order order);
}

package com.dipl.inventory.mapper;

import com.dipl.inventory.api.dto.DecreaseInventoryRequest;
import com.dipl.inventory.message.dto.OrderCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EventMapper {

  DecreaseInventoryRequest fromEvent(OrderCreatedEvent event);

  @Mapping(target = "id", source = "itemId")
  DecreaseInventoryRequest.DecreaseItemRequest fromEvent(OrderCreatedEvent.OrderItem item);
}

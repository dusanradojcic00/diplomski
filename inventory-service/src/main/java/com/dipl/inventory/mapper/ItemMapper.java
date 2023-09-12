package com.dipl.inventory.mapper;

import com.dipl.inventory.api.dto.ItemDto;
import com.dipl.inventory.model.Item;
import com.dipl.inventory.repo.ItemEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface ItemMapper {

  List<Item> fromEntity(List<ItemEntity> entities);
  Item fromEntity(ItemEntity entity);

  List<ItemEntity> toEntity(List<Item> items);

  ItemEntity toEntity(Item item);

  List<ItemDto> toResponse(List<ItemEntity> entities);

  ItemDto toResponse(ItemEntity entity);
}

package com.dipl.order.feign;

import com.dipl.order.feign.dto.ItemDto;
import com.dipl.order.model.Item;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface FeignMapper {


  List<Item> fromDto(List<ItemDto> itemDto);
  Item fromDto(ItemDto itemDto);

}

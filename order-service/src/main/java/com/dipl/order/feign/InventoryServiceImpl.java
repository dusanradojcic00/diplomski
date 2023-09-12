package com.dipl.order.feign;

import com.dipl.order.api.dto.OrderItemDto;
import com.dipl.order.feign.dto.DecreaseInventoryRequest;
import com.dipl.order.model.Item;
import com.dipl.order.service.InventoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

  private final InventoryFeignClient client;
  private final FeignMapper mapper;

  @Override
  public List<Item> getItems(List<Long> ids) {
    return mapper.fromDto(client.getItems(ids));
  }

  @Override
  public void decreaseQuantity(Long orderId, List<OrderItemDto> items) {
    client.decreaseQuantity(new DecreaseInventoryRequest(orderId, items));
  }
}

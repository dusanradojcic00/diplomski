package com.dipl.order.feign;

import com.dipl.order.model.Item;
import com.dipl.order.service.InventoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

  private final InventoryFeignClient feignClient;

  @Override
  public List<Item> getItems(List<Long> ids) {
    return feignClient.getItems(ids);
  }
}

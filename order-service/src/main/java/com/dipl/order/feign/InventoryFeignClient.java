package com.dipl.order.feign;

import com.dipl.order.model.Item;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
    name = "inventory",
    value = "inventory",
    url = "http://localhost:8082/api/v1/inventory")
public interface InventoryFeignClient {

  @GetMapping
  List<Item> getItems(List<Long> ids);
}

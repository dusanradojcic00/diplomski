package com.dipl.order.feign;

import com.dipl.order.feign.dto.DecreaseInventoryRequest;
import com.dipl.order.feign.dto.ItemDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "inventory",
    value = "inventory",
    url = "http://localhost:8082/api/v1/inventory")
public interface InventoryFeignClient {

  @GetMapping
  List<ItemDto> getItems(@RequestParam("ids") List<Long> ids);

  @PostMapping("/decrease")
  void decreaseQuantity(@RequestBody DecreaseInventoryRequest request);
}

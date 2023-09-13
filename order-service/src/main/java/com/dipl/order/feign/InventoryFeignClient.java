package com.dipl.order.feign;

import com.dipl.order.feign.dto.DecreaseInventoryRequest;
import com.dipl.order.feign.dto.ItemDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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
  @Retry(name = "inventory")
  @CircuitBreaker(name = "inventory", fallbackMethod = "getItemsFallback")
  List<ItemDto> getItems(@RequestParam("ids") List<Long> ids);

  default List<ItemDto> getItemsFallback(
      List<Long> ids, RuntimeException exception) {

    throw new RuntimeException("Inventory service is currently unavailable", exception);
  }


  @PostMapping("/decrease")
  void decreaseQuantity(@RequestBody DecreaseInventoryRequest request);
}

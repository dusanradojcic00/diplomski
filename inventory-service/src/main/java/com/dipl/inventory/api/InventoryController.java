package com.dipl.inventory.api;

import com.dipl.inventory.api.dto.DecreaseInventoryRequest;
import com.dipl.inventory.api.dto.ItemDto;
import com.dipl.inventory.service.InventoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

  private final InventoryService inventoryService;

  @GetMapping
  public List<ItemDto> getItemsByIds(@RequestParam("ids") List<Long> itemIds) {
    return inventoryService.getAll(itemIds);
  }

  @PostMapping("/decrease")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void decreaseQuantity(@RequestBody DecreaseInventoryRequest request) {
    inventoryService.decrease(request);
  }
}

package com.dipl.inventory.api;

import com.dipl.inventory.api.dto.ItemDto;
import com.dipl.inventory.service.InventoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

  private final InventoryService inventoryService;

  @GetMapping
  public List<ItemDto> getItemsByIds(@RequestParam("ids") List<Long> itemIds) {
    return inventoryService.getItemsByIds(itemIds);
  }
}

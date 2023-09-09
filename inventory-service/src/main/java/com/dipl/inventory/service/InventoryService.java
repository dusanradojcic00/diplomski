package com.dipl.inventory.service;

import com.dipl.inventory.api.dto.ItemDto;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class InventoryService {

  public List<ItemDto> getItemsByIds(List<Long> itemIds) {
    return null;
  }
}

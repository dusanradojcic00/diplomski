package com.dipl.inventory.service;

import com.dipl.inventory.api.dto.DecreaseInventoryRequest;
import com.dipl.inventory.api.dto.ItemDto;
import com.dipl.inventory.mapper.ItemMapper;
import com.dipl.inventory.model.Item;
import com.dipl.inventory.repo.ItemRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryService {

  private final ItemRepository repository;
  private final ItemMapper itemMapper;

  public List<ItemDto> getAll(List<Long> itemIds) {
    var items = repository.getByIdIn(new HashSet<>(itemIds));

    return itemMapper.toResponse(items);
  }

  @Transactional
  public void decrease(DecreaseInventoryRequest request) {
    var ids = getIds(request.items());
    log.info("Decreasing the inventory of '{}'", ids);

    List<Item> items = itemMapper.fromEntity(repository.getByIdIn(ids));

    items.forEach(item -> {
      request.items().stream()
          .filter(itemDto -> itemDto.id().equals(item.getId()))
          .findFirst()
          .ifPresent(itemDto -> item.decrease(itemDto.quantity()));
    });

    repository.saveAll(itemMapper.toEntity(items));
  }

  private static Set<Long> getIds(List<DecreaseInventoryRequest.DecreaseItemRequest> items) {
    return items.stream()
        .map(DecreaseInventoryRequest.DecreaseItemRequest::id)
        .collect(Collectors.toSet());
  }
}

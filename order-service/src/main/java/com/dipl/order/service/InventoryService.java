package com.dipl.order.service;

import com.dipl.order.model.Item;
import java.util.List;

public interface InventoryService {

  List<Item> getItems(List<Long> ids);
}

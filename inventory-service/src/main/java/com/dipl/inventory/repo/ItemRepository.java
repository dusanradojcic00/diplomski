package com.dipl.inventory.repo;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
  List<ItemEntity> getByIdIn(Set<Long> ids);
}

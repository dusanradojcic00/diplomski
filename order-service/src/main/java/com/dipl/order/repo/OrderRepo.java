package com.dipl.order.repo;

import com.dipl.order.enums.OrderStatus;
import com.dipl.order.repo.entity.OrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Long> {

  List<OrderEntity> findByStatus(OrderStatus orderStatus);
}

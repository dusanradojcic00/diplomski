package com.dipl.order.repo.entity;

import com.dipl.order.api.dto.OrderItemDto;
import com.dipl.order.enums.OrderStatus;
import com.dipl.order.model.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_order")
@EntityListeners(AuditingEntityListener.class)
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "customer_id")
  private Long customerId;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "order_id")
  @ToString.Exclude
  private List<OrderItemEntity> items = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private OrderStatus status;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  public void addItems(List<OrderItemDto> items, List<Item> availableItems) {

    this.items = items.stream()
        .map(item -> {
          var availableItem = availableItems.stream()
              .filter(item2 -> item2.getId().equals(item.id()))
              .findFirst()
              .orElseThrow(() -> new RuntimeException("NOT FOUND"));
          return new OrderItemEntity(this, item.id(), item.quantity(), availableItem.getPrice());
        })
        .toList();
  }
}



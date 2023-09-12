package com.dipl.order.repo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "t_order_item")
public class OrderItemEntity {

  @EmbeddedId
  private OrderItemId id;

  @ToString.Exclude
  @MapsId("orderId")
  @ManyToOne(fetch = FetchType.LAZY)
  private OrderEntity order;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "price_per_unit")
  private Double pricePerUnit;

  @Column(name = "total_price")
  private Double totalPrice;

  public OrderItemEntity(OrderEntity orderEntity, Long itemId, Integer quantity,
      Double pricePerUnit) {

    this.id = new OrderItemId(orderEntity.getId(), itemId);
    this.order = orderEntity;
    this.quantity = quantity;
    this.pricePerUnit = pricePerUnit;
    this.totalPrice = quantity * pricePerUnit;
  }
}
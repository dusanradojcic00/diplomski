package com.dipl.order.repo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemId implements Serializable {

  @Serial
  private static final long serialVersionUID = 7546466293409364184L;

  @Column(name = "order_id")
  private Long orderId;

  @Column(name = "item_id")
  private Long itemId;
}



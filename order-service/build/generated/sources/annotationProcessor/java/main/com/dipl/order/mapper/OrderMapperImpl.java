package com.dipl.order.mapper;

import com.dipl.order.api.dto.CreateOrderRequest;
import com.dipl.order.message.dto.OrderCreatedEvent;
import com.dipl.order.model.Order;
import com.dipl.order.model.OrderItem;
import com.dipl.order.repo.entity.OrderEntity;
import com.dipl.order.repo.entity.OrderItemEntity;
import com.dipl.order.repo.entity.OrderItemId;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-07T02:14:30+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Homebrew)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderEntity toEntity(CreateOrderRequest request) {
        if ( request == null ) {
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setCustomerId( request.customerId() );

        return orderEntity;
    }

    @Override
    public Order fromEntity(OrderEntity orderEntity) {
        if ( orderEntity == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.id( orderEntity.getId() );
        order.customerId( orderEntity.getCustomerId() );
        order.items( orderItemEntityListToOrderItemList( orderEntity.getItems() ) );

        return order.build();
    }

    @Override
    public OrderItem fromEntity(OrderItemEntity orderItemEntity) {
        if ( orderItemEntity == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.orderId( orderItemEntityIdOrderId( orderItemEntity ) );
        orderItem.itemId( orderItemEntityIdItemId( orderItemEntity ) );
        orderItem.quantity( orderItemEntity.getQuantity() );

        return orderItem.build();
    }

    @Override
    public OrderCreatedEvent toOrderCreatedEvent(Order order) {
        if ( order == null ) {
            return null;
        }

        Long id = null;
        Long customerId = null;
        List<OrderItem> items = null;

        id = order.getId();
        customerId = order.getCustomerId();
        List<OrderItem> list = order.getItems();
        if ( list != null ) {
            items = new ArrayList<OrderItem>( list );
        }

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent( id, customerId, items );

        return orderCreatedEvent;
    }

    protected List<OrderItem> orderItemEntityListToOrderItemList(List<OrderItemEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItem> list1 = new ArrayList<OrderItem>( list.size() );
        for ( OrderItemEntity orderItemEntity : list ) {
            list1.add( fromEntity( orderItemEntity ) );
        }

        return list1;
    }

    private Long orderItemEntityIdOrderId(OrderItemEntity orderItemEntity) {
        if ( orderItemEntity == null ) {
            return null;
        }
        OrderItemId id = orderItemEntity.getId();
        if ( id == null ) {
            return null;
        }
        Long orderId = id.getOrderId();
        if ( orderId == null ) {
            return null;
        }
        return orderId;
    }

    private Long orderItemEntityIdItemId(OrderItemEntity orderItemEntity) {
        if ( orderItemEntity == null ) {
            return null;
        }
        OrderItemId id = orderItemEntity.getId();
        if ( id == null ) {
            return null;
        }
        Long itemId = id.getItemId();
        if ( itemId == null ) {
            return null;
        }
        return itemId;
    }
}

package com.dipl.order.api.dto;

import com.dipl.order.model.Order;
import com.dipl.order.model.OrderItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-07T02:16:00+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Homebrew)"
)
@Component
public class OrderDtoMapperImpl implements OrderDtoMapper {

    @Override
    public CreateOrderResponse toResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        Long id = null;
        Long customerId = null;
        List<OrderItemDto> items = null;

        id = order.getId();
        customerId = order.getCustomerId();
        items = orderItemListToOrderItemDtoList( order.getItems() );

        CreateOrderResponse createOrderResponse = new CreateOrderResponse( id, customerId, items );

        return createOrderResponse;
    }

    @Override
    public OrderItemDto toDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        Long id = null;
        Integer quantity = null;

        id = orderItem.getItemId();
        quantity = orderItem.getQuantity();

        OrderItemDto orderItemDto = new OrderItemDto( id, quantity );

        return orderItemDto;
    }

    protected List<OrderItemDto> orderItemListToOrderItemDtoList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemDto> list1 = new ArrayList<OrderItemDto>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( toDto( orderItem ) );
        }

        return list1;
    }
}

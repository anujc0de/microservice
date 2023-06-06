package com.order_service.mapper;

import com.order_service.entities.OrderItem;
import com.order_service.request.OrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);


    Set<OrderItem> orderRequestToOrderItems(Set<OrderItem> orderItems);

}

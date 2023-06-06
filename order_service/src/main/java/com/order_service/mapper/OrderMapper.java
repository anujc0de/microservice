package com.order_service.mapper;


import com.order_service.entities.Order;
import com.order_service.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderResponse orderToOrderResponse(Order order);
}

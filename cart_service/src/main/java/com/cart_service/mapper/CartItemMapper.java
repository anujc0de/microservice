package com.cart_service.mapper;

import com.cart_service.entities.CartItems;
import com.cart_service.request.CartItemRequest;
import com.cart_service.request.CartRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);


    List<CartItems> cartRequestToCartItemsList(List<CartItems> cartItems);

}

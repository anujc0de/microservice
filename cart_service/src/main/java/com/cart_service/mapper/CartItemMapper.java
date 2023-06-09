package com.cart_service.mapper;

import com.cart_service.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);


    Set<CartItem> cartRequestToCartItems(Set<CartItem> cartItems);

}

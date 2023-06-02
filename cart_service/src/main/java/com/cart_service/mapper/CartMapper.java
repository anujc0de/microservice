package com.cart_service.mapper;


import com.cart_service.entities.Cart;
import com.cart_service.response.CartResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartResponse cartToCartResponse(Cart cart);
}

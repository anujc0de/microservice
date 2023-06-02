package com.cart_service.services;

import com.cart_service.entities.Cart;
import com.cart_service.entities.CartItems;
import com.cart_service.repos.CartItemRepository;
import com.cart_service.repos.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarService {

    private  final CartItemRepository cartItemRepository;
    private  final CartRepository cartRepository;

    public CarService(CartItemRepository cartItemRepository, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
    }

    public Cart createCart(Cart cart, List<CartItems> cartItems){

        for (CartItems cartItem : cartItems) {
            cartItem.setId(UUID.randomUUID());

        }
        cart.setCartItems(cartItems);
        return  cartRepository.save(cart);




    }


}

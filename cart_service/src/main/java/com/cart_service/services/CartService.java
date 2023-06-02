package com.cart_service.services;

import com.cart_service.entities.Cart;
import com.cart_service.entities.CartItems;
import com.cart_service.repos.CartItemRepository;
import com.cart_service.repos.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CartService {

    private  final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart addItemsToCart(int customerId, Set<CartItems> cartItems) {
        Cart cart = cartRepository.findCartByCustomerId(customerId).orElseThrow();
        for (CartItems cartItem : cartItems) {
            cartItem.setId(UUID.randomUUID());
            cartItem.setCart(cart);
        }
        cart.setCartItems(cartItems);
        cartRepository.save(cart);
        return cart;
    }


}

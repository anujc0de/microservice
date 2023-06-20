package com.cart_service.impl;

import com.cart_service.entities.Cart;
import com.cart_service.entities.CartItem;
import com.cart_service.mapper.CartMapper;
import com.cart_service.repos.CartItemRepository;
import com.cart_service.repos.CartRepository;
import com.common.activities.CartActivities;
import com.common.response.CartResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class CartActivitiesImpl implements CartActivities {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper = CartMapper.INSTANCE;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartResponse getCart(int customerId) {


        log.info("requesting to get cart with error {}", customerId);
        Cart fetchedCart = cartRepository.findCartByCustomerId(customerId).orElseThrow();
        log.info("Finished to get cart {}", customerId);
        return cartMapper.cartToCartResponse(fetchedCart);
    }


    public Cart getCartByCustomerId(int customerId) {
        return cartRepository.findCartByCustomerId(customerId).orElseThrow();

    }

    @Override
    public void clearCart(int customerId) {
        Cart cart = getCartByCustomerId(customerId);
        List<CartItem> cartItems = new ArrayList<>(cart.getCartItems());

//        for (CartItem cartItem : cartItems) {
//            cartItem.setCart(null);
//            cartItemRepository.save(cartItem);
//        }
//        cartItemRepository.deleteById(cartItems.get(0).getId());
        cartItemRepository.deleteAll(cartItems);

//        cart.getCartItems().clear();

//        cartRepository.save(cart);



    }

    @Override
    public void revertClearCart(int customerId, Set<com.common.inventoryRequests.CartItem> cartItems) {
//call inventory service  and place order in stock


        var cartItems1 = cartItems.stream().map(item -> CartItem.builder().productId(item.getProductId()).quantity(item.getQuantity()).productId(item.getProductId()).build()).collect(Collectors.toSet());

        Cart cart = getCartByCustomerId(customerId);
        for (CartItem cartItem : cartItems1) {
            cartItem.setId(UUID.randomUUID());
            cartItem.setCart(cart);


        }
        cart.setCartItems(cartItems1);
        cartRepository.save(cart);

    }


}

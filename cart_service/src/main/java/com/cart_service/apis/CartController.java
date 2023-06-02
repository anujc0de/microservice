package com.cart_service.apis;

import com.cart_service.entities.Cart;
import com.cart_service.entities.CartItems;
import com.cart_service.mapper.CartItemMapper;
import com.cart_service.mapper.CartMapper;
import com.cart_service.request.CartItemRequest;
import com.cart_service.request.CartRequest;
import com.cart_service.services.CarService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
@Validated
public class CartController {

    Logger log = LoggerFactory.getLogger(CartController.class);
    private final CartItemMapper cartItemMapper = CartItemMapper.INSTANCE;
    private final CartMapper cartMapper = CartMapper.INSTANCE;

    private final CarService carService;

    public CartController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<?> addCart(@Valid @RequestBody CartRequest cartRequest) {
        log.info("requested to add cart");
        var cart = Cart.builder()
                .id(UUID.randomUUID())
                .customerId(cartRequest.getCustomerId())
                .build();
        List<CartItems> cartItemRequests = cartRequest.getCartItems();
        List<CartItems> cartItems = cartItemMapper.cartRequestToCartItemsList(cartItemRequests);

        var saved=carService.createCart(cart,cartItems);
        return new ResponseEntity<>(cartMapper.cartToCartResponse(saved), HttpStatus.CREATED);
    }
}

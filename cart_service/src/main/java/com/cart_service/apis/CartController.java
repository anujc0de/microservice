package com.cart_service.apis;

import com.cart_service.mapper.CartItemMapper;
import com.cart_service.mapper.CartMapper;
import com.cart_service.request.CartRequest;
import com.cart_service.services.CartService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@Validated
public class CartController {

    Logger log = LoggerFactory.getLogger(CartController.class);
    private final CartItemMapper cartItemMapper = CartItemMapper.INSTANCE;
    private final CartMapper cartMapper = CartMapper.INSTANCE;

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<?> addCart(@Valid @RequestBody CartRequest cartRequest) {
        log.info("requested to add cart");

        var cartItemRequests = cartRequest.getCartItems();
        var cartItems = cartItemMapper.cartRequestToCartItems(cartItemRequests);

        var saved = cartService.addItemsToCart(cartRequest.getCustomerId(), cartItems);
        return new ResponseEntity<>(cartMapper.cartToCartResponse(saved), HttpStatus.CREATED);
    }
}

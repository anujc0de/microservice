package com.cart_service.apis;

import com.cart_service.entities.Cart;
import com.cart_service.mapper.CartItemMapper;
import com.cart_service.mapper.CartMapper;
import com.cart_service.request.CartRequest;
import com.cart_service.services.CartService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/carts")
@Validated
@Transactional
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
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<?> getCartsByCustomerId(@PathVariable(value = "customerId") int customerId) {
        log.info("requested to get carts by customerId");

          var fetchedCart=cartService.getCartByCustomerId(customerId);
        return new ResponseEntity<>(cartMapper.cartToCartResponse(fetchedCart), HttpStatus.CREATED);
    }
    @PutMapping("/customers/{customerId}/clear")
    public ResponseEntity<?> clearCart(@PathVariable(value = "customerId") int customerId) {
        log.info("requested to clear cart");

        var optionalCart = cartService.clearCartItems(customerId);
        return new ResponseEntity<>(cartMapper.cartToCartResponse(optionalCart), HttpStatus.CREATED);


    }


}

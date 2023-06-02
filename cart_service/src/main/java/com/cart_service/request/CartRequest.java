package com.cart_service.request;

import com.cart_service.entities.CartItems;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CartRequest {


    public int getCustomerId() {
        return customerId;
    }

    private int customerId;

    public List<CartItems> getCartItems() {
        return cartItems;
    }

    private List<CartItems> cartItems;



}

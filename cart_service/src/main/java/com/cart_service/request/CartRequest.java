package com.cart_service.request;

import com.cart_service.entities.CartItems;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class CartRequest {

    private int customerId;

    private Set<CartItems> cartItems;

}

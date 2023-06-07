package com.cart_service.request;

import com.cart_service.entities.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {

    private int customerId;

    private Set<CartItem> cartItems;

}

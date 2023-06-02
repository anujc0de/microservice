package com.cart_service.response;

import com.cart_service.dto.CartItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
@Data
public class CartResponse {
    private UUID id;

    private int customerId;

    private Instant createdAt;

    private Instant updatedAt;
    private Set<CartItemDto> cartItems;
}

package com.payment_service.response;

import com.payment_service.dto.CartItemDto;
import lombok.Data;

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

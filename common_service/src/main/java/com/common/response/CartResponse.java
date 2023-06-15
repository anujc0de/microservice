package com.common.response;

import com.common.model.CartItemDto;
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

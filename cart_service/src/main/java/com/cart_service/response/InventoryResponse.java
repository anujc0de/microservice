package com.cart_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {
    private UUID id;

    private UUID productId;
    private  int quantity;

    private Instant createdAt;

    private Instant updatedAt;
}

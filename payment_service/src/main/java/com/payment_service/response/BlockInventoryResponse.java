package com.payment_service.response;

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
public class BlockInventoryResponse {
    private UUID id;

    private int productId;
    private  int quantity;

    private Instant expiryTime;

    private Instant createdAt;

    private Instant updatedAt;
}

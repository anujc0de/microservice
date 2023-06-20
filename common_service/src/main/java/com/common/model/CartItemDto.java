package com.common.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@Jacksonized
public class CartItemDto {

    private UUID id;
    private UUID productId;
    private int quantity;
    private  float price;
    private Instant createdAt;

    private Instant updatedAt;

}

package com.payment_service.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class CartItemDto {

    private UUID id;
    private UUID productId;
    private int quantity;
    private  float price;
    private Instant createdAt;

    private Instant updatedAt;

}

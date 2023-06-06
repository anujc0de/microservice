package com.order_service.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class OrderItemDto {

    private UUID id;
    private UUID productId;
    private int quantity;

    private Instant createdAt;

    private Instant updatedAt;

}

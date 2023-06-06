package com.order_service.response;

import com.order_service.dto.OrderItemDto;
import lombok.Data;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
@Data
public class OrderResponse {
    private UUID id;

    private int customerId;

    private Instant createdAt;

    private Instant updatedAt;
    private Set<OrderItemDto> orderItems;
}

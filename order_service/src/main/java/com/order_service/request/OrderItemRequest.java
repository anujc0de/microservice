package com.order_service.request;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class OrderItemRequest {
    private UUID productId;
    private int quantity;
}

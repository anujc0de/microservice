package com.payment_service.orderRequests;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class OrderItem {
    private UUID productId;
    private int quantity;
}

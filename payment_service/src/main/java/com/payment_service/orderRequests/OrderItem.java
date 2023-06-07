package com.payment_service.orderRequests;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OrderItem {
    private UUID productId;
    private int quantity;
}

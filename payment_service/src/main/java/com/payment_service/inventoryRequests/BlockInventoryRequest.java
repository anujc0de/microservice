package com.payment_service.inventoryRequests;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class BlockInventoryRequest {
    private Set<CartItem> cartItems;
}

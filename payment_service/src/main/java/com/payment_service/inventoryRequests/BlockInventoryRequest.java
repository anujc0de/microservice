package com.payment_service.inventoryRequests;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Set;

@Data
@Builder
@Jacksonized
public class BlockInventoryRequest {
    private Set<CartItem> cartItems;
}

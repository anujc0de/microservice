package com.common.inventoryRequests;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class BlockInventoryRequest {
    private Set<CartItem> cartItems;
}

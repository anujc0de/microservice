package com.common.inventoryRequests;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CartItem {

    private UUID productId;
    private int quantity;



}

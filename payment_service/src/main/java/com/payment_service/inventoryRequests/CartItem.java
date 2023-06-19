package com.payment_service.inventoryRequests;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class CartItem {

    private UUID productId;
    private int quantity;



}

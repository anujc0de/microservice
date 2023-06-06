package com.inventory_service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CartItem {

    private UUID productId;
    private int quantity;



}

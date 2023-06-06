package com.inventory_service.request;

import com.inventory_service.dto.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlockInventoryRequest {
    private Set<CartItem> cartItems;
}

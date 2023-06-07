package com.order_service.request;

import com.order_service.entities.OrderItem;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@Jacksonized
public class OrderRequest {

    private int customerId;
    private UUID paymentId;

    private Set<OrderItemRequest> orderItems;

}

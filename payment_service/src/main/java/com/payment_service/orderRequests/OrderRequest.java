package com.payment_service.orderRequests;

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

    private Set<OrderItem> orderItems;

}

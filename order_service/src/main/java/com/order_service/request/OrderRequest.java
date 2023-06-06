package com.order_service.request;

import com.order_service.entities.OrderItem;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class OrderRequest {

    private int customerId;
    private UUID paymentId;

    private Set<OrderItem> orderItems;

}

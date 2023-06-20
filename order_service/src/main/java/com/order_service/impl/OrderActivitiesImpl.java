package com.order_service.impl;

import com.common.activities.OrderActivities;
import com.common.inventoryRequests.CartItem;
import com.order_service.entities.Order;
import com.order_service.entities.OrderItem;
import com.order_service.entities.OrderStatus;
import com.order_service.repos.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class OrderActivitiesImpl implements OrderActivities {

    private final OrderRepository orderRepository;


    @Override
    public void completeOrder(int customerId, UUID paymentId, Set<CartItem> orderItems) {

        Order order = Order.builder()
                .id(UUID.randomUUID())
                .customerId(customerId)
                .orderStatus(OrderStatus.COMPLETED)
                .paymentId(paymentId).build();

        var orderItemsData =orderItems.stream()
                .map(item -> OrderItem.builder().id(UUID.randomUUID()).order(order).productId(item.getProductId()).quantity(item.getQuantity()).build())
                .collect(Collectors.toSet());


        order.setOrderItems(orderItemsData);
        orderRepository.save(order);
    }

    @Override
    public void failOrder(UUID paymentId) {

        Order order=orderRepository.findOrderByPaymentId(paymentId).orElseThrow();
        order.setOrderStatus(OrderStatus.FAILED);
        orderRepository.save(order);



    }
}

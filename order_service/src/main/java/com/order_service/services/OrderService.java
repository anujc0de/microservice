package com.order_service.services;

import com.order_service.entities.Order;
import com.order_service.entities.OrderItem;
import com.order_service.repos.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public Order createOrder(int customerId, UUID paymentId, Set<OrderItem> orderItems) {


        Order order = Order.builder()
                .id(UUID.randomUUID())
                .customerId(customerId)
                .paymentId(paymentId).build();


        for (OrderItem orderItem : orderItems) {
            orderItem.setId(UUID.randomUUID());
            orderItem.setOrder(order);
        }
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        return order;
    }


}

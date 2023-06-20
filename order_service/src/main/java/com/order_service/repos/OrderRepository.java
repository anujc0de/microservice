package com.order_service.repos;

import com.order_service.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    public Optional<Order> findOrderByPaymentId(UUID paymentId);
}

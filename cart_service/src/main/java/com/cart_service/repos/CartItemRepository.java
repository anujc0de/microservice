package com.cart_service.repos;

import com.cart_service.entities.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItems, UUID> {
}

package com.cart_service.repos;

import com.cart_service.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository  extends JpaRepository<Cart, UUID> {
}

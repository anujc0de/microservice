package com.cart_service.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
@Data
public class CartItemDto {

    private UUID id;
    private UUID productId;
    private int quantity;
    private  float price;

    private Instant createdAt;

    private Instant updatedAt;

}

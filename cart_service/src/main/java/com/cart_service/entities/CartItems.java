package com.cart_service.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "cart_items")
public class CartItems {


    @Id
    @Column
    private UUID id;
    @Column
    private UUID productId;

    @Column
    private int quantity;
    @Column
    private  float price;
    @Column
    @CreatedDate
    private Instant createdAt;

    @Column
    @LastModifiedDate
    private Instant updatedAt;


    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;



}

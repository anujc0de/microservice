package com.inventory_service.repos;

import com.inventory_service.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    List<Inventory> findByProductIdIn(List<UUID> productIds);

    @Modifying
    @Transactional
    @Query(value = "UPDATE inventories SET quantity = quantity - :reduceQuantity WHERE product_id = :productId",nativeQuery = true)
    void updateInventoryQuantity(@Param("productId") UUID productId, @Param("reduceQuantity") int reduceQuantity);

}

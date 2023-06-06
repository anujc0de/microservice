package com.inventory_service.repos;

import com.inventory_service.entities.BlockInventory;
import com.inventory_service.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface BlockInventoryRepository extends JpaRepository<BlockInventory, UUID> {
    @Query(value = "SELECT product_id, SUM(quantity) AS totalQuantity FROM block_inventories WHERE product_id IN :productIds AND expiry_time > :expiryTime GROUP BY product_id",nativeQuery = true)
    List<Object[]> findTotalQuantityByProductIds(@Param("productIds") List<UUID> productIds, @Param("expiryTime") Instant expiryTime);

    @Modifying
    @Query(value = "UPDATE block_inventories SET expiry_time = :currentTime WHERE id IN :ids",nativeQuery = true)
    void updateExpiryTimeByIds(@Param("ids") List<UUID> ids, @Param("currentTime") Instant currentTime);
}


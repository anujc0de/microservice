package com.inventory_service.impl;

import com.common.activities.InventoryActivities;
import com.common.inventoryRequests.CartItem;
import com.common.response.BlockInventoryResponse;
import com.inventory_service.entities.BlockInventory;
import com.inventory_service.entities.Inventory;
import com.inventory_service.mapper.BlockInventoryMapper;
import com.inventory_service.repos.BlockInventoryRepository;
import com.inventory_service.repos.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class InventoryActivitiesImpl implements InventoryActivities {

    private final InventoryRepository inventoryRepository;
    private final BlockInventoryMapper blockInventoryMapper = BlockInventoryMapper.INSTANCE;



    private final BlockInventoryRepository blockInventoryRepository;


    public List<Inventory> getInventoriesByProductIds(List<UUID> productIds) {
        return inventoryRepository.findByProductIdIn(productIds);
    }

    @Override
    public void checkInventories(Set<CartItem> cartItems) {

        var productIds=cartItems.stream().map(CartItem::getProductId).toList();
        Instant currentTime = Instant.now();
        List<Object[]> result= blockInventoryRepository.findTotalQuantityByProductIds(productIds, currentTime);
        Map<UUID, Integer> quantityMap = result.stream()
                .collect(Collectors.toMap(
                        array -> (UUID) array[0],   // productId (UUID)
                        array -> (Integer) array[1] // totalQuantity (Integer)
                ));
        Map<UUID, Integer> requestedMap = cartItems.stream()
                .collect(Collectors.toMap(
                        CartItem::getProductId,   // productId (UUID)
                        CartItem::getQuantity // totalQuantity (Integer)
                ));
        List<Inventory> inventories=getInventoriesByProductIds(productIds);
        //check inventory quantity-sum(block_inventory) for given product if it is less than 0 throw error
        for (Inventory inventory : inventories) {
            UUID productId = inventory.getProductId();
            int totalQuantity = inventory.getQuantity();
            int blockedQuantity = quantityMap.getOrDefault(productId, 0);
            int availableQuantity = totalQuantity - blockedQuantity;
            int requestedQuantity=requestedMap.getOrDefault(productId, 0);

            if (availableQuantity < requestedQuantity) {
                // Throw an error or handle the insufficient quantity case
                throw new RuntimeException("Insufficient quantity for product: " + productId);
            }
        }



    }

    @Override
    public List<BlockInventoryResponse> blockInventories(Set<CartItem> cartItems) {
        Instant currentTime = Instant.now();
        List<BlockInventory> blockingInventories = new ArrayList<>();
        for (CartItem cartItem :cartItems.stream().toList() ){

            Instant expiryTime = currentTime.plusSeconds(5);
            BlockInventory blockInventory = BlockInventory.builder()
                    .id(UUID.randomUUID())
                    .productId(cartItem.getProductId())
                    .expiryTime(expiryTime)
                    .quantity(cartItem.getQuantity())
                    .createdAt(Instant.now())
                    .updatedAt(Instant.now())
                    .build();
            blockInventoryRepository.save(blockInventory);
            blockingInventories.add(blockInventory);

        }
      return blockInventoryMapper.blockinventoriesToBlockInventoriesResponse(blockingInventories);

    }

    @Override
    public List<BlockInventoryResponse> unblockInventories(List<UUID>blockedInventoryIds) {
        Instant currentTime = Instant.now();
        blockInventoryRepository.updateExpiryTimeByIds(blockedInventoryIds, currentTime);
        return blockInventoryMapper.blockinventoriesToBlockInventoriesResponse(blockInventoryRepository.findAllById(blockedInventoryIds));

    }

    @Override
    public void updateInventories(List<UUID> blockedInventoryIds) {
        List<BlockInventory> unblockInventories=blockInventoryRepository.findAllById(blockedInventoryIds);

        for (BlockInventory blockInventory : unblockInventories) {

            UUID productId=blockInventory.getProductId();
            int reduceQuantity= blockInventory.getQuantity();
            inventoryRepository.updateInventoryQuantity(productId,reduceQuantity);
        }
    }

    @Override
    public void revertUpdateInventories(List<UUID> blockedInventoryIds) {
        List<BlockInventory> unblockInventories=blockInventoryRepository.findAllById(blockedInventoryIds);

        for (BlockInventory blockInventory : unblockInventories) {

            UUID productId=blockInventory.getProductId();
            int reduceQuantity= -blockInventory.getQuantity();
            inventoryRepository.updateInventoryQuantity(productId,reduceQuantity);
        }
    }
}

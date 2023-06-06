package com.inventory_service.services;

import com.inventory_service.dto.CartItem;
import com.inventory_service.entities.BlockInventory;
import com.inventory_service.entities.Inventory;
import com.inventory_service.repos.BlockInventoryRepository;
import com.inventory_service.repos.InventoryRepository;
import com.inventory_service.request.BlockInventoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final BlockInventoryRepository blockInventoryRepository;

    public List<Inventory> getInventoriesByProductIds(List<UUID> productIds) {
        return inventoryRepository.findByProductIdIn(productIds);
    }
    public   List<BlockInventory>  checkAndBlockInventories(BlockInventoryRequest blockInventoryRequest){
        var productIds=blockInventoryRequest.getCartItems().stream().map(CartItem::getProductId).toList();
        Instant currentTime = Instant.now();
        List<Object[]> result= blockInventoryRepository.findTotalQuantityByProductIds(productIds, currentTime);
        Map<UUID, Integer> quantityMap = result.stream()
                .collect(Collectors.toMap(
                        array -> (UUID) array[0],   // productId (UUID)
                        array -> (Integer) array[1] // totalQuantity (Integer)
                ));
        Map<UUID, Integer> requestedMap = blockInventoryRequest.getCartItems().stream()
                .collect(Collectors.toMap(
                        CartItem::getProductId,   // productId (UUID)
                        CartItem::getQuantity // totalQuantity (Integer)
                ));
        List<Inventory> inventories=getInventoriesByProductIds(productIds);
        //check inventory quantity-sum(block_inventory) for given product if it is less than 0 throw error
        List<BlockInventory> blockingInventories = new ArrayList<>();
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

        for (CartItem cartItem :blockInventoryRequest.getCartItems().stream().toList() ){

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
  return blockingInventories;
    }

    public   List<BlockInventory>  unblockInventories(List<UUID>blockedInventoryIds) {
        Instant currentTime = Instant.now();
        blockInventoryRepository.updateExpiryTimeByIds(blockedInventoryIds, currentTime);
        return blockInventoryRepository.findAllById(blockedInventoryIds);
    }
    public   List<BlockInventory>  unblockInventoriesAndUpdate(List<UUID>blockedInventoryIds) {
        List<BlockInventory> unblockInventories=unblockInventories(blockedInventoryIds);

        //call updateInventoryQuantity to update for all the unblockInventories
        for (BlockInventory blockInventory : unblockInventories) {

             UUID productId=blockInventory.getProductId();
             int quantity= blockInventory.getQuantity();
             inventoryRepository.updateInventoryQuantity(productId,quantity);
        }
        return  unblockInventories;

    }
}

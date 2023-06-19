package com.common.activities;

import com.common.inventoryRequests.CartItem;
import com.common.model.PaymentDto;
import com.common.response.BlockInventoryResponse;
import io.temporal.activity.ActivityInterface;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@ActivityInterface
public interface InventoryActivities {
  void checkInventories(Set<CartItem> cartItems);
  List<BlockInventoryResponse> blockInventories(Set<CartItem> cartItems);
  List<BlockInventoryResponse> unblockInventories(List<UUID>blockedInventoryIds);
  void  updateInventories(List<UUID>blockedInventoryIds);
  void  revertUpdateInventories(List<UUID>blockedInventoryIds);
}

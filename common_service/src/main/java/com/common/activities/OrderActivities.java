package com.common.activities;

import com.common.inventoryRequests.CartItem;
import com.common.model.PaymentDto;
import io.temporal.activity.ActivityInterface;

import java.util.Set;
import java.util.UUID;

@ActivityInterface
public interface OrderActivities {
  void completeOrder(int customerId, UUID paymentId, Set<CartItem> orderItems);
  void failOrder(UUID paymentId);
}

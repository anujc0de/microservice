package com.common.activities;

import com.common.model.PaymentDto;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface InventoryActivities {
  void checkAndBlockInventories(PaymentDto paymentDTO);
  void releaseInventory(PaymentDto paymentDTO);
}

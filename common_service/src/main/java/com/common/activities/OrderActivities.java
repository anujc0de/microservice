package com.common.activities;

import com.common.model.PaymentDto;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface OrderActivities {
  void completeOrder(PaymentDto payment);
  void failOrder(PaymentDto payment);
}

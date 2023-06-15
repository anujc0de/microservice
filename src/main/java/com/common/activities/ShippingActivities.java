package com.common.activities;

import com.common.model.PaymentDto;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface ShippingActivities {
  void shipGoods(PaymentDto payment);
  void cancelShipment(PaymentDto payment);
}

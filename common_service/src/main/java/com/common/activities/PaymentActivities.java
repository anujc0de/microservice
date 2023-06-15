package com.common.activities;

import com.common.model.PaymentDto;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface PaymentActivities {
  void debitPayment(PaymentDto payment);
  void reversePayment(PaymentDto payment);
}

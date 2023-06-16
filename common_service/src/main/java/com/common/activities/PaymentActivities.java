package com.common.activities;

import com.common.model.PaymentDto;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface PaymentActivities {
  @ActivityMethod
  void completePayment(PaymentDto payment,float amout);
  @ActivityMethod
  void failPayment(PaymentDto payment,float amount);
}

package com.payment_service.impl;

import com.common.activities.PaymentActivities;
import com.common.model.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PaymentActivitiesImpl implements PaymentActivities {


  @Override
  public void debitPayment(PaymentDto payment) {
  }

  @Override
  public void reversePayment(PaymentDto payment) {
  }
}

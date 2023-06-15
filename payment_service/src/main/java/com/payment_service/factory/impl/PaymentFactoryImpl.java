package com.payment_service.factory.impl;

import com.payment_service.command.PaymentCommand;
import com.payment_service.factory.PaymentFactory;

public class PaymentFactoryImpl implements PaymentFactory {

  private final PaymentCommand paymentCommand;

  public PaymentFactoryImpl(PaymentCommand paymentCommand) {
    this.paymentCommand = paymentCommand;
  }


  public PaymentCommand getPaymentCommand() {
    return paymentCommand;
  }




}

package com.payment_service.factory.impl;

import com.payment_service.command.PaymentCommand;
import com.payment_service.factory.PaymentFactory;
import com.payment_service.query.PaymentQuery;

public class PaymentFactoryImpl implements PaymentFactory {

  private final PaymentCommand paymentCommand;
  private  final PaymentQuery paymentQuery;

  public PaymentFactoryImpl(PaymentCommand paymentCommand, PaymentQuery paymentQuery) {
    this.paymentCommand = paymentCommand;
    this.paymentQuery = paymentQuery;
  }


  public PaymentCommand getPaymentCommand() {
    return paymentCommand;
  }

  @Override
  public PaymentQuery getPaymentQuery() {
    return paymentQuery;
  }




}

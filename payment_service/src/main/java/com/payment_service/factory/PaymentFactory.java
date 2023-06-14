package com.payment_service.factory;

import com.payment_service.command.PaymentCommand;
import com.payment_service.query.PaymentQuery;

public interface PaymentFactory {

  PaymentCommand getPaymentCommand();
  PaymentQuery getPaymentQuery();

}

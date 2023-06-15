package com.payment_service.factory;

import com.payment_service.command.PaymentCommand;

public interface PaymentFactory {

  PaymentCommand getPaymentCommand();

}

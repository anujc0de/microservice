package com.payment_service.query;

import com.payment_service.response.CartResponse;

public interface PaymentQuery {
  CartResponse getCart(int customerId);
}

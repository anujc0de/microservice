package com.payment_service.orchestration;


import com.payment_service.entities.Payment;

public interface WorkflowOrchestrator {
  void makePayment(Payment payment);
}

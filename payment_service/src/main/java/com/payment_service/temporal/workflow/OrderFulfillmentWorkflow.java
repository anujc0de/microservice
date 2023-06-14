package com.payment_service.temporal.workflow;

import com.payment_service.entities.Payment;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface PaymentFulfillmentWorkflow {
  @WorkflowMethod
  void makePayment(Payment payment);
}

package com.payment_service.command.impl;

import com.payment_service.command.PaymentCommand;
import com.payment_service.entities.Payment;
import com.payment_service.entities.PaymentStatus;
import com.payment_service.factory.PaymentFactory;
import com.payment_service.orchestration.WorkflowOrchestrator;
import com.payment_service.repos.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class PaymentCommandImpl implements PaymentCommand {

  private final PaymentRepository paymentRepository;
  private final WorkflowOrchestrator workflowOrchestrator;
  private  final PaymentFactory paymentFactory;


  @Override
  public Payment makePayment(int customerId) {

    Payment payment = Payment.builder()
            .id(UUID.randomUUID())
            .customerId(customerId)
            .paymentStatus(PaymentStatus.STARTED)
            .createdAt(Instant.now())
            .updatedAt(Instant.now())
            .build();

    log.info("Creating payment..");
    var persistedPayment = paymentRepository.save(payment);
    workflowOrchestrator.makePayment(persistedPayment);
    return persistedPayment;
  }
}

package com.payment_service.temporal.workflow.activity.impl;

import com.common.activities.PaymentActivities;
import com.common.error.ServiceException;
import com.common.model.PaymentDto;
import com.payment_service.entities.Payment;
import com.payment_service.entities.PaymentStatus;
import com.payment_service.repos.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
public class PaymentActivitiesImpl implements PaymentActivities {

    private final PaymentRepository paymentRepository;


    @Override
    public void completePayment(PaymentDto paymentDto, float amount) {
        log.info("Marking payment as completed, payment id {}", paymentDto.getId());

        var payment = map(paymentDto, amount);
        payment.setPaymentStatus(PaymentStatus.COMPLETED);
        var completePayment = paymentRepository.save(payment);
        log.info("payment completed, {}", completePayment);

        log.error("Error occurred while shipping..");

    }


    @Override
    public void failPayment(PaymentDto paymentDto) {
        log.info("Marking order as failed, order id {}", paymentDto.getId());
        var payment = map(paymentDto, 0);
        payment.setPaymentStatus(PaymentStatus.FAILED);
        var failedPayment = paymentRepository.save(payment);
        log.info("payment failed, {}", failedPayment);

    }

    private Payment map(PaymentDto paymentDto,float amount) {
        var payment = new Payment();
        payment.setId(paymentDto.getId());
        payment.setAmount(amount);
        payment.setCustomerId(paymentDto.getCustomerId());
        payment.setCreatedAt(Instant.now());
        payment.setUpdatedAt(Instant.now());
        return payment;
    }

}

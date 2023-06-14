package com.payment_service.apis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payment_service.factory.PaymentFactory;
import com.payment_service.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@Transactional
@RequiredArgsConstructor
public class PaymentController {

    private  final PaymentFactory paymentFactory;
    private final PaymentService paymentService;
    @PostMapping("/customers/{customerId}")
    public ResponseEntity<?> createPayment(@PathVariable(value = "customerId") int customerId) throws JsonProcessingException {


        var paymentCommand =paymentFactory.getPaymentCommand();
        var pendingPayment=paymentCommand.makePayment(customerId);

        return  new ResponseEntity<>(pendingPayment, HttpStatus.OK);

    }
}

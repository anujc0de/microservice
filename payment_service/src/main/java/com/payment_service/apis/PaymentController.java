package com.payment_service.apis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payment_service.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/payments")
@Transactional
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    @PostMapping("/customers/{customerId}")
    public ResponseEntity<?> createPayment(@PathVariable(value = "customerId") int customerId) throws JsonProcessingException {

        return  new ResponseEntity<>(paymentService.makePayment(customerId), HttpStatus.OK);

    }
}

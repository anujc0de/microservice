package com.payment_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class PaymentController {

    @GetMapping
    public  String payment(){
        return "I am payment service";
    }
}

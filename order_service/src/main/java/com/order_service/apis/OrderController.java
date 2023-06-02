package com.order_service.apis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class OrderController {

    @GetMapping
    public  String order(){
        return "I am order service";
    }
}

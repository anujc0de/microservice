package com.order_service.apis;

import com.order_service.mapper.OrderItemMapper;
import com.order_service.mapper.OrderMapper;
import com.order_service.request.OrderRequest;
import com.order_service.services.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Transactional
public class OrderController {
    Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderItemMapper orderItemMapper = OrderItemMapper.INSTANCE;
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;
    private  final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        log.info("requested to create order");

        var orderItemRequest = orderRequest.getOrderItems();
        var orderItems = orderItemMapper.orderItemRequestsToOrderItems(orderItemRequest);

        var saved = orderService.createOrder(orderRequest.getCustomerId(),orderRequest.getPaymentId(),orderItems);
        return new ResponseEntity<>(orderMapper.orderToOrderResponse(saved), HttpStatus.CREATED);
    }
}

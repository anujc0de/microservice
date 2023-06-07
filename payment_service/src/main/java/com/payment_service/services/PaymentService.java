package com.payment_service.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment_service.dto.CartItemDto;
import com.payment_service.entities.Payment;
import com.payment_service.inventoryRequests.BlockInventoryRequest;
import com.payment_service.inventoryRequests.CartItem;
import com.payment_service.orderRequests.OrderItem;
import com.payment_service.orderRequests.OrderRequest;
import com.payment_service.repos.PaymentRepository;
import com.payment_service.response.BlockInventoryResponse;
import com.payment_service.response.CartResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final WebClient.Builder webClientBuilder;
    Logger log = LoggerFactory.getLogger(PaymentService.class);
    private final Random random = new Random();
    private final PaymentRepository paymentRepository;

    public Payment makePayment(int customerId) throws JsonProcessingException {
        log.info("request to make payment");

        log.info("payment started");


        CartResponse cartResponse = webClientBuilder.build().get()
                .uri("http://localhost:8081/carts/customers/{customerId}", customerId)
                .retrieve()
                .bodyToMono(CartResponse.class)
                .block();

        if (cartResponse == null || cartResponse.getCartItems() == null || cartResponse.getCartItems().isEmpty()) {
            throw new RuntimeException("Cannot make payment. Cart is empty.");
        }


        var cartItems = cartResponse.getCartItems().stream()
                .map(item -> CartItem.builder().productId(item.getProductId()).quantity(item.getQuantity()).build())
                .collect(Collectors.toSet());

        BlockInventoryRequest blockInventoryRequest = BlockInventoryRequest.builder().cartItems(cartItems).build();

        BlockInventoryResponse[] blockInventoryResponses = webClientBuilder.build()
                .post()
                .uri("http://localhost:8083/inventories/check")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(blockInventoryRequest), BlockInventoryRequest.class)// Set the request body
                .retrieve()
                .bodyToMono(BlockInventoryResponse[].class)
                .block();


        int sleepDuration = random.nextInt(1000) + 1000;

        try {
            Thread.sleep(sleepDuration);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assert blockInventoryResponses != null;
        List<UUID> blockedInventoryIds = Arrays.stream(blockInventoryResponses).map(BlockInventoryResponse::getId).toList();
        if (random.nextDouble() < 0.2) {
            //unblock
            BlockInventoryResponse[] inventoryResponses = webClientBuilder.build().put()
                    .uri("http://localhost:8083/inventories/unblock",
                            uriBuilder -> uriBuilder.queryParam("blockedInventoryIds", blockedInventoryIds).build())
                    .retrieve()
                    .bodyToMono(BlockInventoryResponse[].class)
                    .block();

            throw new RuntimeException("Payment failed. Please try again.");
        }

        float totalAmount = (float) cartResponse.getCartItems().stream().mapToDouble(CartItemDto::getPrice).sum();

        Payment payment = Payment.builder()
                .id(UUID.randomUUID())
                .customerId(customerId)
                .amount(totalAmount)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        var saved = paymentRepository.save(payment);
        //ubblock and inventory update

        BlockInventoryResponse[] inventoryResponses = webClientBuilder.build().put()
                .uri("http://localhost:8083/inventories/unblock-and-update",
                        uriBuilder -> uriBuilder.queryParam("blockedInventoryIds", blockedInventoryIds).build())
                .retrieve()
                .bodyToMono(BlockInventoryResponse[].class)
                .block();

        webClientBuilder.build().put()
                .uri("http://localhost:8081/carts/customers/{customerId}/clear", customerId)
                .retrieve()
                .bodyToMono(CartResponse.class)
                .block();
        //create order

        var orderItems = cartResponse.getCartItems().stream()
                .map(item -> OrderItem.builder().productId(item.getProductId()).quantity(item.getQuantity()).build())
                .collect(Collectors.toSet());

        OrderRequest orderRequest = OrderRequest.builder()
                .paymentId(payment.getId())
                .customerId(customerId)
                .orderItems(orderItems).build();


        webClientBuilder.build()
                .post()
                .uri("http://localhost:8084/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(orderRequest), OrderRequest.class)// Set the request body
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info("Payment successful");


        return saved;


    }

}

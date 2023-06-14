package com.payment_service.query.impl;

import com.payment_service.query.PaymentQuery;
import com.payment_service.response.CartResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RequiredArgsConstructor
public class PaymentQueryImpl implements PaymentQuery {
    private final WebClient.Builder webClientBuilder;

    @Override
    public CartResponse getCart(int customerId) {
        log.info("Fetching Order for id {}", customerId);

        CartResponse cartResponse = webClientBuilder.build().get()
                .uri("http://localhost:8081/carts/customers/{customerId}", customerId)
                .retrieve()
                .bodyToMono(CartResponse.class)
                .block();

        if (cartResponse == null || cartResponse.getCartItems() == null || cartResponse.getCartItems().isEmpty()) {
            throw new RuntimeException("Cannot make payment. Cart is empty.");
        }
        return cartResponse;

    }
}

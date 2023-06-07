package com.cart_service.services;

import com.cart_service.entities.Cart;
import com.cart_service.entities.CartItem;
import com.cart_service.repos.CartItemRepository;
import com.cart_service.repos.CartRepository;
import com.cart_service.response.InventoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final WebClient.Builder webClientBuilder;
    private  final CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, WebClient.Builder webClientBuilder, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.webClientBuilder = webClientBuilder;
        this.cartItemRepository = cartItemRepository;
    }

    public Cart addItemsToCart(int customerId, Set<CartItem> cartItems) {

        //call inventory service  and place order in stock
        List<UUID> productIds = cartItems.stream().map(CartItem::getProductId).toList();

        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://localhost:8083/inventories",
                        uriBuilder -> uriBuilder.queryParam("productIds", productIds).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();


        Map<UUID, Integer> inventoryQuantityMap = new HashMap<>();
        assert inventoryResponses != null;
        for (InventoryResponse inventoryResponse : inventoryResponses) {
            inventoryQuantityMap.put(inventoryResponse.getProductId(), inventoryResponse.getQuantity());
        }


        Cart cart = cartRepository.findCartByCustomerId(customerId).orElseThrow();
        for (CartItem cartItem : cartItems) {
            UUID productId = cartItem.getProductId();
            int quantity = cartItem.getQuantity();

            if (inventoryQuantityMap.containsKey(productId)) {
                int availableQuantity = inventoryQuantityMap.get(productId);
                if (quantity <= availableQuantity) {
                    inventoryQuantityMap.put(productId, availableQuantity - quantity);
                    cartItem.setId(UUID.randomUUID());
                    cartItem.setCart(cart);
                } else {
                    // Handle insufficient quantity error
                    throw new RuntimeException("Insufficient quantity for product: " + productId);
                }
            } else {
                // Handle product not found error
                throw new RuntimeException("Product not found: " + productId);
            }
        }
        cart.setCartItems(cartItems);
        cartRepository.save(cart);
        return cart;
    }

    public Cart getCartByCustomerId(int customerId) {
        return cartRepository.findCartByCustomerId(customerId).orElseThrow();

    }

    public Cart clearCartItems(int customerId) {
        Cart cart = getCartByCustomerId(customerId);
        List<CartItem> cartItems = new ArrayList<>(cart.getCartItems());

        for (CartItem cartItem : cartItems) {
            cartItem.setCart(null);
        }

        cart.getCartItems().clear();
        cartRepository.save(cart);

        cartItemRepository.deleteAll(cartItems);

        return cart;
    }
}

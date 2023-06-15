package com.cart_service.impl;

import com.cart_service.entities.Cart;
import com.cart_service.mapper.CartMapper;
import com.cart_service.repos.CartRepository;
import com.common.activities.CartActivities;
import com.common.response.CartResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CartActivitiesImpl implements CartActivities {

  private final CartRepository cartRepository;
  private final CartMapper cartMapper = CartMapper.INSTANCE;

  @Override
  public CartResponse getCart(int customerId) {

    log.info("requesting to get cart {}", customerId);

    Cart fetchedCart= cartRepository.findCartByCustomerId(customerId).orElseThrow();
    log.info("Finished to get cart {}", customerId);

   return cartMapper.cartToCartResponse(fetchedCart);


  }


}

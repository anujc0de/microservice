package com.common.activities;

import com.common.inventoryRequests.CartItem;
import com.common.model.CartItemDto;
import com.common.response.CartResponse;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import io.temporal.workflow.Functions;

import java.util.Set;

@ActivityInterface
public interface CartActivities {
    @ActivityMethod
    CartResponse getCart(int customerId);
    void  clearCart(int customerId);
    void  revertClearCart(int customerId, Set<CartItem> cartItems);

}

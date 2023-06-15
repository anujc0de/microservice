package com.common.activities;

import com.common.response.CartResponse;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface CartActivities {
    CartResponse getCart(int customerId);

}

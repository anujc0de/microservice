package com.common.activities;

import com.common.response.CartResponse;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import io.temporal.workflow.Functions;

@ActivityInterface
public interface CartActivities {
    @ActivityMethod
    CartResponse getCart(int customerId);

    @ActivityMethod
    Functions.Proc failCart();

}

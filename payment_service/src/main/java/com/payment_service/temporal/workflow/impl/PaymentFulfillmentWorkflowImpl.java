package com.payment_service.temporal.workflow.impl;

import com.common.TaskQueue;
import com.common.activities.*;
import com.common.model.PaymentDto;
import com.common.response.CartResponse;
import com.payment_service.inventoryRequests.CartItem;
import com.payment_service.temporal.workflow.PaymentFulfillmentWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.activity.LocalActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.failure.ActivityFailure;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.stream.Collectors;

public class PaymentFulfillmentWorkflowImpl implements PaymentFulfillmentWorkflow {

    private final Logger logger = Workflow.getLogger(this.getClass().getName());

    private final ActivityOptions cartActivityOptions =
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofMinutes(1))
                    .setTaskQueue(TaskQueue.CART_ACTIVITY_TASK_QUERY.name())
                    .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(3).build())
                    .build();

//    private final ActivityOptions shippingActivityOptions =
//            ActivityOptions.newBuilder()
//                    .setStartToCloseTimeout(Duration.ofMinutes(1))
//                    .setTaskQueue(TaskQueue.SHIPPING_ACTIVITY_TASK_QUEUE.name())
//                    .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(3).build())
//                    .build();
//
//    private final ActivityOptions orderActivityOptions =
//            ActivityOptions.newBuilder()
//                    .setStartToCloseTimeout(Duration.ofMinutes(1))
//                    .setTaskQueue(TaskQueue.ORDER_ACTIVITY_TASK_QUEUE.name())
//                    .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(3).build())
//                    .build();
//    private final ActivityOptions inventoryActivityOptions =
//            ActivityOptions.newBuilder()
//                    .setStartToCloseTimeout(Duration.ofMinutes(1))
//                    .setTaskQueue(TaskQueue.INVENTORY_ACTIVITY_TASK_QUEUE.name())
//                    .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(3).build())
//                    .build();
    private final LocalActivityOptions localActivityOptions =
            LocalActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofMinutes(1))
                    .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(10).build())
                    .build();

    private final PaymentActivities paymentActivities =
            Workflow.newLocalActivityStub(PaymentActivities.class, localActivityOptions);

    private final CartActivities cartActivities =
            Workflow.newActivityStub(CartActivities.class, cartActivityOptions);

//    private final OrderActivities orderActivities =
//            Workflow.newActivityStub(OrderActivities.class, orderActivityOptions);
//
//    private final InventoryActivities inventoryActivities =
//            Workflow.newActivityStub(InventoryActivities.class, inventoryActivityOptions);
//
//    private final ShippingActivities shippingActivities =
//            Workflow.newActivityStub(ShippingActivities.class, shippingActivityOptions);

    @Override
    public void makePayment(PaymentDto paymentDto) {
        // Configure SAGA to run compensation activities in parallel
        Saga.Options sagaOptions = new Saga.Options.Builder().setParallelCompensation(true).build();
        Saga saga = new Saga(sagaOptions);
        try {
            CartResponse cartResponse= cartActivities.getCart(paymentDto.getCustomerId());

            var cartItems = cartResponse.getCartItems().stream()
                    .map(item -> CartItem.builder().productId(item.getProductId()).quantity(item.getQuantity()).build())
                    .collect(Collectors.toSet());
            System.out.println(cartItems);

//            paymentActivities.debitPayment(paymentDto);
//            saga.addCompensation(paymentActivities::reversePayment, paymentDto);
//            //Inventory
//            inventoryActivities.reserveInventory(paymentDto);
//            saga.addCompensation(inventoryActivities::releaseInventory, paymentDto);
//            //Shipping
//            shippingActivities.shipGoods(paymentDto);
//            saga.addCompensation(shippingActivities::cancelShipment, paymentDto);
//            //Order
//            orderActivities.completeOrder(paymentDto);
//            saga.addCompensation(orderActivities::failOrder, paymentDto);
        } catch (ActivityFailure cause) {
            saga.compensate();
            throw cause;
        }
    }
}
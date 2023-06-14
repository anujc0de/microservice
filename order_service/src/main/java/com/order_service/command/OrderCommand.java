package com.order_service.command;


import com.order_service.entities.Order;

public interface OrderCommand {
  Order createOrder(Order order);
}

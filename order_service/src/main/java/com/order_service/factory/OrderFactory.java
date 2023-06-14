package com.order_service.factory;

import com.order_service.command.OrderCommand;

public interface OrderFactory {

  OrderCommand getOrderCommand();

}

package com.order_service.command.impl;

import com.order_service.command.OrderCommand;
import com.order_service.entities.Order;
import com.order_service.entities.OrderStatus;
import com.order_service.orchestration.WorkflowOrchestrator;
import com.order_service.repos.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class OrderCommandImpl implements OrderCommand {

  private final OrderRepository orderRepository;
  private final WorkflowOrchestrator workflowOrchestrator;

  @Override
  public Order createOrder(Order order) {
    log.info("Creating order..");
    order.setOrderStatus(OrderStatus.PENDING);
    var persistedOrder = orderRepository.save(order);
    workflowOrchestrator.createOrder(persistedOrder);
    return persistedOrder;
  }
}

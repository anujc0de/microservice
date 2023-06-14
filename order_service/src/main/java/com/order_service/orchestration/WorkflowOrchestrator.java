package com.order_service.orchestration;


import com.order_service.entities.Order;

public interface WorkflowOrchestrator {
  void createOrder(Order order);
}

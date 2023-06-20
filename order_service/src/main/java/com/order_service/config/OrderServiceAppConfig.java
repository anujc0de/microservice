package com.order_service.config;

import com.common.activities.OrderActivities;
import com.order_service.impl.OrderActivitiesImpl;
import com.order_service.orchestrator.WorkflowOrchestratorClient;
import com.order_service.repos.OrderRepository;
import com.order_service.worker.OrderWorker;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class OrderServiceAppConfig {

  @Bean
  public OrderWorker orderWorker( OrderRepository orderRepository) {
    return new OrderWorker(
            getOrderActivity(orderRepository), workflowOrchestratorClient());
  }

  @Bean
  public OrderActivities getOrderActivity( OrderRepository orderRepository) {
    return new OrderActivitiesImpl(orderRepository);
  }

  @Bean
  @ConfigurationProperties
  public ApplicationProperties applicationProperties() {
    return new ApplicationProperties();
  }

  @Bean
  public WorkflowOrchestratorClient workflowOrchestratorClient() {
    return new WorkflowOrchestratorClient(applicationProperties());
  }


}

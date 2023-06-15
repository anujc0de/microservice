package com.cart_service.config;

import com.cart_service.impl.CartActivitiesImpl;
import com.cart_service.repos.CartRepository;
import com.cart_service.temporal.orchestrator.WorkflowOrchestratorClient;
import com.cart_service.worker.CartWorker;
import com.common.activities.CartActivities;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class CartServiceAppConfig {

  @Bean
  public CartWorker cartWorker(CartRepository cartRepository) {
    return new CartWorker(
        getCartActivity(cartRepository), workflowOrchestratorClient());
  }

  @Bean
  public CartActivities getCartActivity(
      CartRepository cartRepository) {
    return new CartActivitiesImpl(cartRepository);
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

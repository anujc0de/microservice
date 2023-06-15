package com.payment_service.config;

import com.payment_service.command.PaymentCommand;
import com.payment_service.command.impl.PaymentCommandImpl;
import com.payment_service.factory.PaymentFactory;
import com.payment_service.factory.impl.PaymentFactoryImpl;
import com.payment_service.orchestration.WorkflowOrchestrator;
import com.payment_service.repos.PaymentRepository;
import com.payment_service.temporal.orchestrator.WorkflowOrchestratorClient;
import com.payment_service.temporal.orchestrator.WorkflowOrchestratorImpl;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class OrderServiceAppConfig {

  @Bean
  public PaymentFactory paymentFactory(PaymentRepository paymentRepository) {
    return new PaymentFactoryImpl(paymentCommand(paymentRepository));
  }



  @Bean
  public PaymentCommand paymentCommand(PaymentRepository paymentRepository) {
    return new PaymentCommandImpl(paymentRepository, workflowOrchestrator());
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


  @Bean
  public WorkflowOrchestrator workflowOrchestrator() {
    return new WorkflowOrchestratorImpl(workflowOrchestratorClient(), applicationProperties());
  }
}

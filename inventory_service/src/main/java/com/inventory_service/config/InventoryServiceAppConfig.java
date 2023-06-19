package com.inventory_service.config;


import com.common.activities.InventoryActivities;
import com.inventory_service.impl.InventoryActivitiesImpl;
import com.inventory_service.orchestrator.WorkflowOrchestratorClient;
import com.inventory_service.repos.BlockInventoryRepository;
import com.inventory_service.repos.InventoryRepository;
import com.inventory_service.worker.InventoryWorker;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class InventoryServiceAppConfig {

  @Bean
  public InventoryWorker inventoryWorker(InventoryRepository inventoryRepository, BlockInventoryRepository blockInventoryRepository) {
    return new InventoryWorker(
            getInventoryActivity(inventoryRepository,blockInventoryRepository), workflowOrchestratorClient());
  }

  @Bean
  public InventoryActivities getInventoryActivity(
          InventoryRepository inventoryRepository,BlockInventoryRepository blockInventoryRepository) {
    return new InventoryActivitiesImpl(inventoryRepository,blockInventoryRepository);
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

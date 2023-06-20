package com.order_service.orchestrator;

import com.order_service.config.ApplicationProperties;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkflowOrchestratorClient {
  private final ApplicationProperties applicationProperties;

  public WorkflowClient getWorkflowClient() {
    System.out.println("dfsdfs"+applicationProperties.getTarget());
    var workflowServiceStubsOptions =
        WorkflowServiceStubsOptions.newBuilder()
            .setTarget(applicationProperties.getTarget())
            .build();
      var workflowServiceStubs = WorkflowServiceStubs.newInstance(workflowServiceStubsOptions);
      return WorkflowClient.newInstance(workflowServiceStubs);

  }
}

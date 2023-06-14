package com.payment_service.temporal.orchestrator;

import com.payment_service.config.ApplicationProperties;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import lombok.RequiredArgsConstructor;
import io.temporal.client.WorkflowClient;

@RequiredArgsConstructor
public class WorkflowOrchestratorClient {
  private final ApplicationProperties applicationProperties;

  public WorkflowClient getWorkflowClient() {
    var workflowServiceStubsOptions =
            WorkflowServiceStubsOptions.newBuilder()
                    .setTarget(applicationProperties.getTarget())
                    .build();
    var workflowServiceStubs = WorkflowServiceStubs.newInstance(workflowServiceStubsOptions);
    return WorkflowClient.newInstance(workflowServiceStubs);
  }
}

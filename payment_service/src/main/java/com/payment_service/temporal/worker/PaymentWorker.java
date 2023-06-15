package com.payment_service.temporal.worker;

import com.common.TaskQueue;
import com.common.activities.PaymentActivities;
import com.payment_service.temporal.orchestrator.WorkflowOrchestratorClient;
import com.payment_service.temporal.workflow.PaymentFulfillmentWorkflow;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerOptions;
import io.temporal.worker.WorkflowImplementationOptions;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PaymentWorker {

  private final PaymentActivities paymentActivities;
  private final WorkflowOrchestratorClient workflowOrchestratorClient;

  @PostConstruct
  public void createWorker() {

    log.info("Registering worker..");

    var workerOptions = WorkerOptions.newBuilder().build();

    var workflowClient = workflowOrchestratorClient.getWorkflowClient();
    WorkflowImplementationOptions workflowImplementationOptions =
        WorkflowImplementationOptions.newBuilder()
            .setFailWorkflowExceptionTypes(NullPointerException.class)
            .build();

    var workerFactory = WorkerFactory.newInstance(workflowClient);
    var worker =
        workerFactory.newWorker(
                TaskQueue.PAYMENT_FULFILLMENT_WORKFLOW_TASK_QUEUE.name(), workerOptions);

    // Can be called multiple times
    worker.registerWorkflowImplementationTypes(
        workflowImplementationOptions, PaymentFulfillmentWorkflow.class);

    worker.registerActivitiesImplementations(paymentActivities);

    workerFactory.start();

    log.info("Registering order worker..");
  }
}



package com.cart_service.worker;

import com.cart_service.temporal.orchestrator.WorkflowOrchestratorClient;
import com.common.TaskQueue;
import com.common.activities.CartActivities;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerOptions;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class CartWorker {

  private final CartActivities cartActivities;
  private final WorkflowOrchestratorClient workflowOrchestratorClient;

  @PostConstruct
  public void createWorker() {

    log.info("Registering worker..");

    var workerOptions = WorkerOptions.newBuilder().build();

    var workflowClient = workflowOrchestratorClient.getWorkflowClient();

    var workerFactory = WorkerFactory.newInstance(workflowClient);
    var worker =
        workerFactory.newWorker(TaskQueue.CART_ACTIVITY_TASK_QUERY.name(), workerOptions);

    worker.registerActivitiesImplementations(cartActivities);

    workerFactory.start();

    log.info("cart worker started..");
  }
}

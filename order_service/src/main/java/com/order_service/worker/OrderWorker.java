package com.order_service.worker;

import com.common.TaskQueue;
import com.common.activities.OrderActivities;
import com.order_service.orchestrator.WorkflowOrchestratorClient;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerOptions;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class OrderWorker {

  private final OrderActivities orderActivities;
  private final WorkflowOrchestratorClient workflowOrchestratorClient;

  @PostConstruct
  public void createWorker() {

    log.info("Registering order  worker..");

    var workerOptions = WorkerOptions.newBuilder().build();

    var workflowClient = workflowOrchestratorClient.getWorkflowClient();

    var workerFactory = WorkerFactory.newInstance(workflowClient);
    var worker =
        workerFactory.newWorker(TaskQueue.ORDER_ACTIVITY_TASK_QUEUE.name(), workerOptions);

    worker.registerActivitiesImplementations(orderActivities);

    workerFactory.start();

    log.info("order worker started..");
  }
}

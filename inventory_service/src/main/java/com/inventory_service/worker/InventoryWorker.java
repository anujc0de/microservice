package com.inventory_service.worker;

import com.common.TaskQueue;
import com.common.activities.InventoryActivities;
import com.inventory_service.orchestrator.WorkflowOrchestratorClient;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerOptions;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class InventoryWorker {

  private final InventoryActivities inventoryActivities;
  private final WorkflowOrchestratorClient workflowOrchestratorClient;

  @PostConstruct
  public void createWorker() {

    log.info("Registering inventory worker..");

    var workerOptions = WorkerOptions.newBuilder().build();

    var workflowClient = workflowOrchestratorClient.getWorkflowClient();

    var workerFactory = WorkerFactory.newInstance(workflowClient);
    var worker =
        workerFactory.newWorker(TaskQueue.INVENTORY_ACTIVITY_TASK_QUEUE.name(), workerOptions);

    worker.registerActivitiesImplementations(inventoryActivities);

    workerFactory.start();

    log.info("inventory worker started..");
  }
}

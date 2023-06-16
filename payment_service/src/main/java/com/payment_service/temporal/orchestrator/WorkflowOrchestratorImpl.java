package com.payment_service.temporal.orchestrator;

import com.common.TaskQueue;
import com.common.model.PaymentDto;
import com.payment_service.config.ApplicationProperties;
import com.payment_service.entities.Payment;
import com.payment_service.orchestration.WorkflowOrchestrator;
import com.payment_service.temporal.workflow.PaymentFulfillmentWorkflow;
import com.payment_service.temporal.workflow.impl.PaymentFulfillmentWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

@RequiredArgsConstructor
public class WorkflowOrchestratorImpl implements WorkflowOrchestrator {

  private final WorkflowOrchestratorClient workflowOrchestratorClient;
  private final ApplicationProperties applicationProperties;

  @Override
  public void makePayment(Payment payment) {

    var paymentDto = map(payment);

    var workflowClient = workflowOrchestratorClient.getWorkflowClient();
    var orderFulfillmentWorkflow =
        workflowClient.newWorkflowStub(
            PaymentFulfillmentWorkflow.class,
            WorkflowOptions.newBuilder()
                .setWorkflowId(applicationProperties.getWorkflowId() + "-" + paymentDto.getId())
                    .setWorkflowExecutionTimeout(Duration.ofSeconds(20))
                .setTaskQueue(TaskQueue.PAYMENT_FULFILLMENT_WORKFLOW_TASK_QUEUE.name())
                .build());
    // Execute Sync
    //    orderFulfillmentWorkflow.createOrder(orderDTO);
    // Async execution
    WorkflowClient.start(orderFulfillmentWorkflow::makePayment, paymentDto);
  }

  private PaymentDto map(Payment payment) {
    var paymentDTO = new PaymentDto();
    paymentDTO.setId(payment.getId());
    paymentDTO.setCustomerId(payment.getCustomerId());
    return paymentDTO;
  }
}

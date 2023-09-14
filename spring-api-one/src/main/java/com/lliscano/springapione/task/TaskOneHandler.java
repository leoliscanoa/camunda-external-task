package com.lliscano.springapione.task;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@ExternalTaskSubscription("example-topic-one")
@AllArgsConstructor
@Slf4j
public class TaskOneHandler implements ExternalTaskHandler {
    private final Random random = new Random();
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        log.debug("EXTERNAL TASK - STARTED: {}",externalTask.getExecutionId());
        VariableMap variables = Variables.createVariables();
        variables.put("age",random.nextInt(101));
        variables.put("gender", random.nextBoolean() ? "MALE" : "FEMALE");
        externalTaskService.complete(externalTask,variables);
        log.debug("EXTERNAL TASK - DONE!: {} ", externalTask.getExecutionId());
    }
}

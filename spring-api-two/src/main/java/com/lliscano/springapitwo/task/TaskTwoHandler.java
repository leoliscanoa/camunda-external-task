package com.lliscano.springapitwo.task;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("example-topic-two")
@AllArgsConstructor
@Slf4j
public class TaskTwoHandler implements ExternalTaskHandler {
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        log.debug("EXTERNAL TASK - STARTED: {}",externalTask.getExecutionId());
        log.debug("EXTERNAL TASK - VARIABLES: {}",externalTask.getAllVariables());
        final Integer age = externalTask.getVariable("age");
        final String gender = externalTask.getVariable("gender");
        VariableMap variables = Variables.createVariables();
        variables.put("message", "YOU ARE A "+age.toString()+ " YEARS OLD "+gender);
        externalTaskService.complete(externalTask,variables);
        log.debug("EXTERNAL TASK - DONE!: {} ", externalTask);
    }
}

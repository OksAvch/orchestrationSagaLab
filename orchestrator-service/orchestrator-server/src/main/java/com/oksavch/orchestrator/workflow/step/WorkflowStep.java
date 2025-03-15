package com.oksavch.orchestrator.workflow.step;

import com.oksavch.orchestrator.enums.WorkflowStepStatus;
import reactor.core.publisher.Mono;

public interface WorkflowStep {

    WorkflowStepStatus getStatus();
    Mono<Boolean> process();
    Mono<Boolean> revert();

}

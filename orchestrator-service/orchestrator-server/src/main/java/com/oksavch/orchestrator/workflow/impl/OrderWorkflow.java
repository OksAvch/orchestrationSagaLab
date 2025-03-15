package com.oksavch.orchestrator.workflow.impl;

import com.oksavch.orchestrator.workflow.Workflow;
import com.oksavch.orchestrator.workflow.step.WorkflowStep;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderWorkflow implements Workflow {

    private final List<WorkflowStep> steps;

    @Override
    public List<WorkflowStep> getSteps() {
        return this.steps;
    }

}

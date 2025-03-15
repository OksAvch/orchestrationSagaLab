package com.oksavch.orchestrator.workflow;

import com.oksavch.orchestrator.workflow.step.WorkflowStep;

import java.util.List;

public interface Workflow {

    List<WorkflowStep> getSteps();

}

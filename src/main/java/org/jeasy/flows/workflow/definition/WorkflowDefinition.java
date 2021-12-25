package org.jeasy.flows.workflow.definition;

import org.jeasy.flows.workflow.WorkFlow;

/**
 * This class defines a {@link WorkFlow} with a hard coded {@link WorkflowKey}.
 * Implementations of this class should not change because there could be persisted instances of this workflow
 * that cause undefined behavior if they resume with a changed definition/instance of this Workflow.
 *
 * @implSpec this class is thread-safe
 * @implNote instances of this class should be immutable
 */
public interface WorkflowDefinition {

    /**
     * creates a new instance of the defined {@link WorkFlow}.
     * @return the defined {@link WorkFlow}
     */
    WorkFlow createWorkflow();

    /**
     * the version of this workflow definition.
     * @return Version of the created workflows
     */
    WorkflowKey getWorkflowKey();

}

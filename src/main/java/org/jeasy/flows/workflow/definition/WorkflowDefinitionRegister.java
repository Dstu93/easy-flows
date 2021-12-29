package org.jeasy.flows.workflow.definition;

import java.util.Optional;

/**
 * Class for registering and holding the {@link WorkflowDefinition} to make workflows known to the engine
 */
public interface WorkflowDefinitionRegister {

    /**
     * registers the {@link WorkflowDefinition}.
     * If there is already an instance registered with the same {@link WorkflowKey} this registered definition will be ignored
     *
     * @param definition the workflow definition to register
     * @throws NullPointerException if definition or the WorkflowKey is null
     */
    void register(WorkflowDefinition definition);

    /**
     * unregisters the {@link WorkflowDefinition} associated with this {@link WorkflowKey}.
     *
     * @param workflowKey - workflowKey, not null
     * @return true if a WorkflowDefinition was unregistered, false if there was no definition with this key
     * @throws NullPointerException if workflowKey is null
     */
    boolean unregister(WorkflowKey workflowKey);

    /**
     * search for a workflow definition by its key
     * @param key - the key of the workflow
     * @return the WorkflowDefinition or {@link Optional#empty()} if nothing found
     */
    Optional<WorkflowDefinition> findByKey(WorkflowKey key);

}

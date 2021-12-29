package org.jeasy.flows.workflow.definition;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Register for {@link WorkflowDefinition}. This class can be used to register {@link WorkflowDefinition}
 * and find them by {@link WorkflowKey}.
 */
public class MapWorkflowRegister implements WorkflowDefinitionRegister {

    private final ConcurrentMap<WorkflowKey, WorkflowDefinition> definitionRegister = new ConcurrentHashMap<>();

    @Override
    public void register(WorkflowDefinition definition) {
        Objects.requireNonNull(definition, "cant register a null value");
        WorkflowKey workflowKey = definition.getWorkflowKey();
        Objects.requireNonNull(workflowKey, "cant register WorkflowDefinition with 'null' as WorkflowKey");
        definitionRegister.putIfAbsent(workflowKey, definition);
    }

    @Override
    public boolean unregister(WorkflowKey workflowKey) {
        Objects.requireNonNull(workflowKey, "cant unregister 'null' as WorkflowDefinition");
        return definitionRegister.remove(workflowKey) != null;
    }

    @Override
    public Optional<WorkflowDefinition> findByKey(WorkflowKey key) {
        return Optional.ofNullable(definitionRegister.get(key));
    }

}

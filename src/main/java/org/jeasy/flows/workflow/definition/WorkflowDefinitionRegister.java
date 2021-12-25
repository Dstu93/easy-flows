package org.jeasy.flows.workflow.definition;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Register for {@link WorkflowDefinition}. This class can be used to register {@link WorkflowDefinition}
 * and find them by {@link WorkflowKey}.
 */
public final class WorkflowDefinitionRegister {

    private static final ConcurrentMap<WorkflowKey, WorkflowDefinition> definitionRegister = new ConcurrentHashMap<>();

    private WorkflowDefinitionRegister() {
        throw new AssertionError("no instance for you!");
    }

    /**
     * registers the {@link WorkflowDefinition}.
     * If there is already an instance registered with the same {@link WorkflowKey} this registered definition will be ignored
     *
     * @param definition the workflow definition to register
     * @throws NullPointerException if definition or the WorkflowKey is null
     */
    public static void register(WorkflowDefinition definition) {
        Objects.requireNonNull(definition, "cant register a null value");
        WorkflowKey workflowKey = definition.getWorkflowKey();
        Objects.requireNonNull(workflowKey, "cant register WorkflowDefinition with 'null' as WorkflowKey");
        definitionRegister.putIfAbsent(workflowKey, definition);
    }

    /**
     * unregisters the {@link WorkflowDefinition} associated with this {@link WorkflowKey}.
     * @param workflowKey - workflowKey, not null
     * @return true if a WorkflowDefinition was unregistered, false if there was no definition with this key
     * @throws NullPointerException if workflowKey is null
     */
    public static boolean unregister(WorkflowKey workflowKey) {
        Objects.requireNonNull(workflowKey, "cant unregister 'null' as WorkflowDefinition");
        return definitionRegister.remove(workflowKey) != null;
    }

    /**
     * search the {@link WorkflowDefinition} in the register associated with this {@link WorkflowKey}
     * @param key - the workflow key
     * @return WorkflowDefinition or {@link Optional#empty()} if there is no {@link WorkflowDefinition} with this key
     */
    public static Optional<WorkflowDefinition> findByKey(WorkflowKey key) {
        return Optional.ofNullable(definitionRegister.get(key));
    }

}

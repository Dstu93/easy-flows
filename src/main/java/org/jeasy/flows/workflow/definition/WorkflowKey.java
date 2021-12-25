package org.jeasy.flows.workflow.definition;

import java.util.Objects;

/**
 * Composed key of a certain Workflow in a specific Version
 */
public final class WorkflowKey implements Comparable<WorkflowKey> {

    private final String workflowUUID;
    private final int version;

    public WorkflowKey(String workflowUUID, int version) {
        this.workflowUUID = workflowUUID;
        this.version = version;
    }

    public String getWorkflowUUID() {
        return workflowUUID;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkflowKey version1 = (WorkflowKey) o;
        return version == version1.version && Objects.equals(workflowUUID, version1.workflowUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workflowUUID, version);
    }

    @Override
    public String toString() {
        return "WorkflowKey{" +
                "workflowUUID='" + workflowUUID + '\'' +
                ", version=" + version +
                '}';
    }

    @Override
    public int compareTo(WorkflowKey o) {
        return Integer.compare(this.version, o.version);
    }
}

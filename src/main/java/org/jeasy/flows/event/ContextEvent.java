package org.jeasy.flows.event;

import org.jeasy.flows.work.context.WorkContext;

/**
 * Event with a {@link WorkContext} to send data to a workflow instance
 */
public class ContextEvent implements ExternalEvent{

    private final String eventId;
    private final WorkContext externalContext;

    public ContextEvent(String eventId, WorkContext externalContext) {
        this.eventId = eventId;
        this.externalContext = externalContext;
    }

    public String getEventId() {
        return eventId;
    }

    public WorkContext getExternalContext() {
        return externalContext;
    }
}

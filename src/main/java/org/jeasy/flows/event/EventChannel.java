package org.jeasy.flows.event;

import java.util.concurrent.CompletableFuture;

/**
 * Simple and small EventChannel to send events to an instance of a workflow
 */
public interface EventChannel {

    /**
     * sends the given event to the workflow with the specific instanceId
     * @param instanceId - unique id of the workflow to identify the recipient of the event
     * @param event - the given event
     *
     * @return a future to check the state of the event
     */
    CompletableFuture<EventState> send(String instanceId, ExternalEvent event);

}

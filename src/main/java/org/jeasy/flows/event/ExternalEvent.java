package org.jeasy.flows.event;

/**
 * Event from an external source
 */
public interface ExternalEvent {

    /**
     * unique id of this event.
     * @return string, unique event id. Never null
     */
    String getEventId();

}

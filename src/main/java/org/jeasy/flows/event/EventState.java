package org.jeasy.flows.event;

/**
 * State of an event after sending
 */
public enum EventState {

    /**
     * The given Event was successfully processed
     */
    PROCESSED,

    /**
     * The workflow instance is busy and not ready to process the event
     */
    NOT_READY,

    /**
     * the event was discarded for unknown reasons
     */
    DISCARDED,
}

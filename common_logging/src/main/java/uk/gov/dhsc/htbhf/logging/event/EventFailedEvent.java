package uk.gov.dhsc.htbhf.logging.event;

import lombok.Builder;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an event that has failed.
 */
public class EventFailedEvent extends Event {

    public static final String FAILED_EVENT_KEY = "failedEvent";
    public static final String FAILURE_DESCRIPTION_KEY = "failureDescription";

    @Builder
    public EventFailedEvent(String failureDescription, Event failedEvent) {
        super(CommonEventType.FAILED_EVENT, failedEvent.getTimestamp(), constructMetadata(failureDescription, failedEvent));
    }

    private static Map<String, Object> constructMetadata(String failureDescription, Event failedEvent) {
        Map<String, Object> metadata = new HashMap<>(failedEvent.getEventMetadata());
        metadata.put(FAILED_EVENT_KEY, failedEvent.getEventType());
        metadata.put(FAILURE_DESCRIPTION_KEY, failureDescription);
        return metadata;
    }

}

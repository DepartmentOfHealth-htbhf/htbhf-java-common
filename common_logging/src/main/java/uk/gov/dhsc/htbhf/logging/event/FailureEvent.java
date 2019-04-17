package uk.gov.dhsc.htbhf.logging.event;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an event that has failed.
 */
public class FailureEvent extends Event {

    public static final String FAILED_EVENT_KEY = "failedEvent";
    public static final String FAILURE_DESCRIPTION_KEY = "failureDescription";

    @Builder
    public FailureEvent(String failureDescription, Event failedEvent) {
        super(
                CommonEventType.FAILURE,
                notNullEvent(failedEvent).getTimestamp(),
                constructMetadata(failureDescription, notNullEvent(failedEvent))
        );
    }

    private static Event notNullEvent(Event failedEvent) {
        return failedEvent == null ? new Event(null, LocalDateTime.now(), null) : failedEvent;
    }

    private static Map<String, Object> constructMetadata(String failureDescription, Event failedEvent) {
        Map<String, Object> metadata = new HashMap<>(failedEvent.getEventMetadata());
        metadata.put(FAILED_EVENT_KEY, failedEvent.getEventType());
        metadata.put(FAILURE_DESCRIPTION_KEY, failureDescription);
        return metadata;
    }

}

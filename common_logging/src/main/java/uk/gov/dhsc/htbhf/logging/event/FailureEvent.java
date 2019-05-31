package uk.gov.dhsc.htbhf.logging.event;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static uk.gov.dhsc.htbhf.logging.ExceptionDetailGenerator.constructExceptionDetail;
import static uk.gov.dhsc.htbhf.logging.event.CommonEventType.FAILURE;
import static uk.gov.dhsc.htbhf.logging.event.CommonEventType.UNKNOWN;

/**
 * Represents an event that has failed.
 */
public class FailureEvent extends Event {

    public static final String FAILED_EVENT_KEY = "failedEvent";
    public static final String FAILURE_DESCRIPTION_KEY = "failureDescription";
    public static final String EXCEPTION_DETAIL_KEY = "exceptionDetail";

    @Builder
    public FailureEvent(String failureDescription, Event failedEvent, Exception exception) {
        super(
                FAILURE,
                notNullEvent(failedEvent).getTimestamp(),
                constructMetadata(failureDescription, notNullEvent(failedEvent), exception)
        );
    }

    private static Event notNullEvent(Event failedEvent) {
        return failedEvent == null ? new Event(UNKNOWN, LocalDateTime.now(), emptyMap()) : failedEvent;
    }

    private static Map<String, Object> constructMetadata(String failureDescription, Event failedEvent, Exception exception) {
        Map<String, Object> metadata = new HashMap<>(failedEvent.getEventMetadata());
        metadata.put(FAILED_EVENT_KEY, failedEvent.getEventType());
        metadata.put(FAILURE_DESCRIPTION_KEY, failureDescription);
        String exceptionDetail = (exception == null) ? null : constructExceptionDetail(exception);
        metadata.put(EXCEPTION_DETAIL_KEY, exceptionDetail);
        return metadata;
    }

}

package uk.gov.dhsc.htbhf.logging.event;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * An {@link Event} marking the start of an application instance.
 */
public class ApplicationStartedEvent extends Event {

    @Builder
    public ApplicationStartedEvent(String applicationVersion, String applicationId, String instanceIndex) {
        super(CommonEventType.APPLICATION_STARTED,
                LocalDateTime.now(),
                constructMetadata(applicationVersion, applicationId, instanceIndex));
    }

    private static Map<String, Object> constructMetadata(String applicationVersion, String applicationId, String instanceIndex) {
        return Map.of(
                "applicationVersion", applicationVersion,
                "applicationId", applicationId,
                "instanceIndex", instanceIndex
        );
    }
}

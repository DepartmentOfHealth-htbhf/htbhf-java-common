package uk.gov.dhsc.htbhf.logging.event;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.gov.dhsc.htbhf.logging.event.EventFailedEvent.FAILED_EVENT_KEY;
import static uk.gov.dhsc.htbhf.logging.event.EventFailedEvent.FAILURE_DESCRIPTION_KEY;

class EventFailedEventTest {

    @Test
    void shouldIncludeDetailsOfFailedEvent() {
        // given
        ApplicationStartedEvent event = ApplicationStartedEvent.builder()
                .instanceIndex("1")
                .applicationId("foo")
                .applicationVersion("1.0.0")
                .build();

        // when
        EventFailedEvent failedEvent = EventFailedEvent.builder()
                .failedEvent(event)
                .failureDescription("myExceptionMessage")
                .build();

        // then
        Map<String, Object> metadata = failedEvent.getEventMetadata();
        assertThat(metadata).containsAllEntriesOf(event.getEventMetadata());
        assertThat(metadata.get(FAILED_EVENT_KEY)).isEqualTo(event.getEventType());
        assertThat(metadata.get(FAILURE_DESCRIPTION_KEY)).isEqualTo("myExceptionMessage");
    }
}
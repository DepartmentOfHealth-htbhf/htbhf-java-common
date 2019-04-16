package uk.gov.dhsc.htbhf.logging;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.dhsc.htbhf.logging.event.ApplicationStartedEvent;
import uk.gov.dhsc.htbhf.logging.event.CommonEventType;
import uk.gov.dhsc.htbhf.logging.event.Event;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.slf4j.event.Level.INFO;
import static uk.gov.dhsc.htbhf.logging.TestConstants.EVENT;
import static uk.gov.dhsc.htbhf.logging.TestEventType.NEW_CLAIM;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {LoggingConfiguration.class})
class EventLoggerSpringConfigTest extends AbstractLoggingTest {

    private static final String EVENT_AS_STRING =
            "{\"eventType\":\"NEW_CLAIM\",\"timestamp\":\"2019-04-10T09:29:09.917131\",\"description\":\"A new claim has been submitted\"}";
    private static final String EVENT_AS_STRING_WITH_NO_METADATA =
            "{\"eventType\":\"NEW_CLAIM\",\"timestamp\":\"2019-04-10T09:29:09.917131\"}";
    private static final String EVENT_AS_STRING_WITH_ORDERED_METADATA =
            "{\"eventType\":\"NEW_CLAIM\",\"timestamp\":\"2019-04-10T09:29:09.917131\",\"aardvark\":\"no\",\"zoo\":\"yes\"}";
    private static final String EVENT_AS_STRING_WITHOUT_NEWLINES =
            "{\"eventType\":\"NEW_CLAIM\",\"timestamp\":\"2019-04-10T09:29:09.917131\",\"multiline\":\"a\\nb\"}";

    @Autowired
    private EventLogger eventLogger;

    @Test
    void shouldLogEvent() {
        //When
        eventLogger.logEvent(EVENT);
        //Then
        assertSingleLogMessageHasText(EVENT_AS_STRING, INFO);
    }

    @Test
    void shouldLogEventWithNoMetadata() {
        //Given
        Event event = new Event(NEW_CLAIM, TestConstants.CREATED_TIME, null);
        //When
        eventLogger.logEvent(event);
        //Then
        assertSingleLogMessageHasText(EVENT_AS_STRING_WITH_NO_METADATA, INFO);
    }

    @Test
    void shouldLogNullEvent() {
        //Given
        Event event = null;
        //When
        eventLogger.logEvent(event);
        //Then
        assertSingleLogMessageHasText("null", INFO);
    }

    @Test
    void shouldReplaceNewLinesWhenLogging() {
        //Given
        Event event = new Event(NEW_CLAIM, TestConstants.CREATED_TIME, Map.of("multiline", "a\nb"));
        //When
        eventLogger.logEvent(event);
        //Then
        assertSingleLogMessageHasText(EVENT_AS_STRING_WITHOUT_NEWLINES, INFO);
    }

    @Test
    void shouldLogMetadataInOrderGivenDataNotInOrder() {
        //Given
        Map<String, Object> metadataNotInOrder = new LinkedHashMap<>();
        metadataNotInOrder.put("zoo", "yes");
        metadataNotInOrder.put("aardvark", "no");
        Event event = new Event(NEW_CLAIM, TestConstants.CREATED_TIME, metadataNotInOrder);
        //When
        eventLogger.logEvent(event);
        //Then
        assertSingleLogMessageHasText(EVENT_AS_STRING_WITH_ORDERED_METADATA, INFO);
    }

    @Test
    void shouldLogEventSubclass() {
        //Given
        ApplicationStartedEvent applicationStartedEvent = ApplicationStartedEvent.builder()
                .applicationId("myApp")
                .applicationVersion("1.0.1")
                .instanceIndex("1")
                .build();
        //When
        eventLogger.logEvent(applicationStartedEvent);

        //Then
        assertSingleLogMessageContainsText("{\"eventType\":\"" + CommonEventType.APPLICATION_STARTED + "\",\"timestamp\":", INFO);
        assertSingleLogMessageContainsText("\"applicationId\":\"myApp\",\"applicationVersion\":\"1.0.1\",\"instanceIndex\":\"1\"}", INFO);
    }

}

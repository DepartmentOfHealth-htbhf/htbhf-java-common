package uk.gov.dhsc.htbhf.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import org.junit.jupiter.api.AfterEach;
import org.slf4j.event.Level;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

abstract class AbstractLoggingTest {

    @AfterEach
    void clearLogEvents() {
        TestAppender.clearAllEvents();
    }

    void assertCorrectLoggingMessage(String expectedMessage, Level expectedLevel) {
        ILoggingEvent loggingEvent = assertSingleLoggingEvent(expectedLevel);
        assertThat(loggingEvent.getMessage()).isEqualTo(expectedMessage);
    }

    void assertSingleLogMessageContainsText(String messageSubstring, Level expectedLevel) {
        ILoggingEvent loggingEvent = assertSingleLoggingEvent(expectedLevel);
        assertThat(loggingEvent.getMessage()).containsSequence(messageSubstring);
    }

    private ILoggingEvent assertSingleLoggingEvent(Level expectedLevel) {
        List<ILoggingEvent> loggingEvents = TestAppender.events;
        assertThat(loggingEvents).hasSize(1);
        ILoggingEvent loggingEvent = loggingEvents.get(0);
        assertThat(loggingEvent.getLevel().toString()).isEqualTo(expectedLevel.toString());
        return loggingEvent;
    }
}

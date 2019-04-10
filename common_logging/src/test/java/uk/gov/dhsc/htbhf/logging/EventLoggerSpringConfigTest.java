package uk.gov.dhsc.htbhf.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.gov.dhsc.htbhf.logging.TestConstants.EVENT;
import static uk.gov.dhsc.htbhf.logging.TestConstants.EVENT_AS_STRING;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {LoggingConfiguration.class})
class EventLoggerSpringConfigTest {

    @Autowired
    private EventLogger eventLogger;

    @AfterEach
    void clearLogger() {
        TestAppender.clearAllEvents();
    }

    @Test
    void testLoggingEvent() {
        //When
        eventLogger.logEvent(EVENT);
        //Then
        List<ILoggingEvent> loggingEvents = TestAppender.events;
        assertThat(loggingEvents).hasSize(1);
        ILoggingEvent loggingEvent = loggingEvents.get(0);
        assertThat(loggingEvent.getMessage()).isEqualTo(EVENT_AS_STRING);
        assertThat(loggingEvent.getLevel().toString()).isEqualTo(Level.INFO.toString());
    }

}

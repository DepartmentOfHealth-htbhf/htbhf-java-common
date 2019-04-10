package uk.gov.dhsc.htbhf.logging;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static uk.gov.dhsc.htbhf.logging.TestConstants.EVENT;
import static uk.gov.dhsc.htbhf.logging.TestConstants.EVENT_AS_STRING;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {LoggingConfiguration.class})
class EventLoggerSpringConfigTest extends AbstractLoggingTest {

    @Autowired
    private EventLogger eventLogger;

    @Test
    void testLoggingEvent() {
        //When
        eventLogger.logEvent(EVENT);
        //Then
        assertCorrectLoggingMessage(EVENT_AS_STRING, Level.INFO);
    }

}

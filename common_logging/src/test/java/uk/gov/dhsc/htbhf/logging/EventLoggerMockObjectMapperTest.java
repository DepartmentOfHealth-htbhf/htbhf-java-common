package uk.gov.dhsc.htbhf.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.event.Level;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static uk.gov.dhsc.htbhf.logging.TestConstants.EVENT;

@ExtendWith(MockitoExtension.class)
class EventLoggerMockObjectMapperTest {

    @Mock
    private ObjectMapper mockObjectMapper;

    @InjectMocks
    private EventLogger eventLogger;

    @AfterEach
    void clearLogger() {
        TestAppender.clearAllEvents();
    }

    @Test
    void shouldStillLogToStringWhenJsonSerialisationFails() throws JsonProcessingException {
        //Given
        given(mockObjectMapper.writeValueAsString(any())).willThrow(new JsonMappingException(null, "Test exception"));
        //When
        eventLogger.logEvent(EVENT);
        //Then
        List<ILoggingEvent> loggingEvents = TestAppender.events;
        assertThat(loggingEvents).hasSize(1);
        ILoggingEvent loggingEvent = loggingEvents.get(0);
        assertThat(loggingEvent.getMessage()).isEqualTo("Unable to write event as JSON, reverting to standard toString(): " + EVENT.toString());
        assertThat(loggingEvent.getLevel().toString()).isEqualTo(Level.WARN.toString());
    }

}

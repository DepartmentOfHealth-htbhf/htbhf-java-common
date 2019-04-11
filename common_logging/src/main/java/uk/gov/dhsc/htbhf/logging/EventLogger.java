package uk.gov.dhsc.htbhf.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class EventLogger {

    private ObjectMapper objectMapper;

    /**
     * Takes the provided event and transforms it into JSON and logs it out. If there are any problems
     * in creating the JSON, the standard toString() format of the Event will be shown.
     *
     * @param event The event to log.
     */
    public void logEvent(Event event) {
        try {
            if (log.isInfoEnabled()) {
                log.info(objectMapper.writeValueAsString(event));
            }
        } catch (JsonProcessingException e) {
            if (log.isWarnEnabled()) {
                log.warn("Unable to write event as JSON, reverting to standard toString(): " + event, e);
            }
        }
    }
}

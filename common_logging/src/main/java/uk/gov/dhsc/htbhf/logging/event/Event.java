package uk.gov.dhsc.htbhf.logging.event;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Data;
import uk.gov.dhsc.htbhf.logging.EventType;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@Data
public class Event {
    private final EventType eventType;
    private final LocalDateTime timestamp;
    private final Map<String, Object> eventMetadata;

    //This is here to make sure that the field "eventMetadata" isn't shown in the logs
    @JsonAnyGetter
    public Map<String, Object> getEventMetadata() {
        return eventMetadata == null ? Collections.emptyMap() : new TreeMap<>(eventMetadata);
    }
}

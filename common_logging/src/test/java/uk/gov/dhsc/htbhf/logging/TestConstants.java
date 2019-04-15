package uk.gov.dhsc.htbhf.logging;

import uk.gov.dhsc.htbhf.logging.event.Event;

import java.time.LocalDateTime;
import java.util.Map;

public class TestConstants {

    public static final LocalDateTime CREATED_TIME = LocalDateTime.parse("2019-04-10T09:29:09.917131");
    public static final Event EVENT = new Event(
            TestEventType.NEW_CLAIM,
            CREATED_TIME,
            Map.of("description", "A new claim has been submitted")
    );

}

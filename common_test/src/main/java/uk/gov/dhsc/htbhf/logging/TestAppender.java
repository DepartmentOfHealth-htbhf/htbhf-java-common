package uk.gov.dhsc.htbhf.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.ArrayList;
import java.util.List;

public class TestAppender extends AppenderBase<ILoggingEvent> {
    private static List<ILoggingEvent> events = new ArrayList<>();

    public static void clearAllEvents() {
        events = new ArrayList<>();
    }

    public static List<ILoggingEvent> getEvents() {
        return events;
    }

    @Override
    protected void append(ILoggingEvent e) {
        events.add(e);
    }
}

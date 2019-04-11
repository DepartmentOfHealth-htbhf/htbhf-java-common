package uk.gov.dhsc.htbhf.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.ArrayList;
import java.util.List;

public class TestAppender extends AppenderBase<ILoggingEvent> {
    static List<ILoggingEvent> events = new ArrayList<>();

    public static void clearAllEvents() {
        events = new ArrayList<>();
    }

    @Override
    protected void append(ILoggingEvent e) {
        events.add(e);
    }
}

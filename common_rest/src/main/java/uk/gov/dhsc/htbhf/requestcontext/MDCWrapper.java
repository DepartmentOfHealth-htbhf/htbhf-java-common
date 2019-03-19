package uk.gov.dhsc.htbhf.requestcontext;

import org.slf4j.MDC;

/**
 * A component wrapper around the MDC that supports non-static access to MDC methods.
 */
public class MDCWrapper {

    public void put(String key, String value) {
        MDC.put(key, value);
    }

    public void remove(String key) {
        MDC.remove(key);
    }
}

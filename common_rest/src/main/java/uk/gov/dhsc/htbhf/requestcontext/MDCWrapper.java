package uk.gov.dhsc.htbhf.requestcontext;

import org.slf4j.MDC;

/**
 * A component wrapper around the MDC that supports non-static access to MDC methods.
 */
public class MDCWrapper {

    /**
     * The name of the request ID in the MDC context. Must match the name in logback configuration.
     */
    public static final String REQUEST_ID_MDC_KEY = "request.id";
    /**
     * The name of the session ID in the MDC context. Must match the name in logback configuration.
     */
    public static final String SESSION_ID_MDC_KEY = "session.id";

    public void put(String key, String value) {
        MDC.put(key, value);
    }

    public void remove(String key) {
        MDC.remove(key);
    }

    /**
     * Remove both request and session id from the MDC context.
     */
    public void clear() {
        this.remove(REQUEST_ID_MDC_KEY);
        this.remove(SESSION_ID_MDC_KEY);
    }
}

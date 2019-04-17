package uk.gov.dhsc.htbhf.requestcontext;

import lombok.Data;

/**
 * A container for context values such as the id of the current request.
 */
@Data
public class RequestContext {

    private String requestId;
    private String sessionId;
    private String method;
    private String servletPath;
}

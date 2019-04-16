package uk.gov.dhsc.htbhf.requestcontext;

/**
 * A container for thread context values such as the id of the current request.
 */
@SuppressWarnings("PMD.DataClass")
public class RequestContext {

    private final ThreadLocal<String> requestId = new ThreadLocal<>();
    private final ThreadLocal<String> sessionId = new ThreadLocal<>();
    private final ThreadLocal<String> method = new ThreadLocal<>();
    private final ThreadLocal<String> servletPath = new ThreadLocal<>();

    public String getMethod() {
        return method.get();
    }

    public void setMethod(String method) {
        this.method.set(method);
    }

    public String getServletPath() {
        return servletPath.get();
    }

    public void setServletPath(String servletPath) {
        this.servletPath.set(servletPath);
    }

    public String getRequestId() {
        return requestId.get();
    }

    public void setRequestId(String requestId) {
        this.requestId.set(requestId);
    }

    public String getSessionId() {
        return sessionId.get();
    }

    public void setSessionId(String sessionId) {
        this.sessionId.set(sessionId);
    }
}

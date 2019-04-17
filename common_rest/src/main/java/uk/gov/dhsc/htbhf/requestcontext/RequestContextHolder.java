package uk.gov.dhsc.htbhf.requestcontext;

/**
 * Wraps a {@link RequestContext} in a {@link ThreadLocal} field.
 */
public class RequestContextHolder {

    private final ThreadLocal<RequestContext> requestContext = ThreadLocal.withInitial(RequestContext::new);

    public RequestContext get() {
        return this.requestContext.get();
    }

    public void clear() {
        this.requestContext.remove();
    }

    public void set(RequestContext requestContext) {
        this.requestContext.set(requestContext);
    }
}

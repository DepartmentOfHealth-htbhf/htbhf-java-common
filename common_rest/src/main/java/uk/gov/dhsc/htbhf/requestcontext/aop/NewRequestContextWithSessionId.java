package uk.gov.dhsc.htbhf.requestcontext.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker identifying that the annotated method should be run with a {@link uk.gov.dhsc.htbhf.requestcontext.RequestContext}
 * and {@link org.slf4j.MDC} that have the given sessionId and an empty requestId.
 * Useful for annotating scheduled methods.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NewRequestContextWithSessionId {

    /**
     * The session id to put in the context.
     * @return the session id to put in the context.
     */
    String sessionId();
}

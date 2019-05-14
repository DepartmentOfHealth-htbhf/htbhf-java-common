package uk.gov.dhsc.htbhf.requestcontext.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import uk.gov.dhsc.htbhf.requestcontext.MDCWrapper;
import uk.gov.dhsc.htbhf.requestcontext.RequestContext;
import uk.gov.dhsc.htbhf.requestcontext.RequestContextHolder;

import java.lang.reflect.Method;

import static uk.gov.dhsc.htbhf.requestcontext.MDCWrapper.REQUEST_ID_MDC_KEY;
import static uk.gov.dhsc.htbhf.requestcontext.MDCWrapper.SESSION_ID_MDC_KEY;

@RequiredArgsConstructor
public class RequestContextAdvisor {

    private final RequestContextHolder requestContextHolder;
    private final MDCWrapper mdcWrapper;

    @Around("@annotation(NewRequestContextWithSessionId)")
    public Object updateRequestContext(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            String sessionId = getSessionIdFromMethodAnnotation(joinPoint);
            String requestId = "";

            RequestContext requestContext = requestContextHolder.get();
            requestContext.setRequestId(requestId);
            requestContext.setSessionId(sessionId);
            mdcWrapper.put(REQUEST_ID_MDC_KEY, requestId);
            mdcWrapper.put(SESSION_ID_MDC_KEY, sessionId);

            return joinPoint.proceed();
        } finally {
            requestContextHolder.clear();
            mdcWrapper.remove(REQUEST_ID_MDC_KEY);
            mdcWrapper.remove(SESSION_ID_MDC_KEY);
        }
    }

    private String getSessionIdFromMethodAnnotation(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        NewRequestContextWithSessionId annotation = method.getAnnotation(NewRequestContextWithSessionId.class);
        return annotation.sessionId();
    }
}

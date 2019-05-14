package uk.gov.dhsc.htbhf.requestcontext.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.dhsc.htbhf.requestcontext.MDCWrapper;
import uk.gov.dhsc.htbhf.requestcontext.RequestContext;
import uk.gov.dhsc.htbhf.requestcontext.RequestContextHolder;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.inOrder;

@ExtendWith(MockitoExtension.class)
class RequestContextAdvisorTest {

    private static final Object RETURN_VALUE = new Object();
    private static final String SESSION_ID = "mySessionId";

    @Mock
    RequestContextHolder requestContextHolder;
    @Mock
    RequestContext requestContext;
    @Mock
    MDCWrapper mdcWrapper;
    @Mock
    MethodSignature methodSignature;
    @Mock
    ProceedingJoinPoint joinPoint;

    Method method = getAnnotatedMethod();

    @InjectMocks
    RequestContextAdvisor advisor;

    @Test
    void shouldPopulateRequestContextWithSessionId() throws Throwable {
        givenJoinPointHasMethodSignature();
        given(requestContextHolder.get()).willReturn(requestContext);

        Object result = advisor.updateRequestContext(joinPoint);

        assertThat(result).isEqualTo(RETURN_VALUE);
        InOrder inOrder = inOrder(joinPoint, requestContext, mdcWrapper, requestContextHolder);
        inOrder.verify(requestContext).setSessionId(SESSION_ID);
        inOrder.verify(mdcWrapper).put(MDCWrapper.SESSION_ID_MDC_KEY, SESSION_ID);
        inOrder.verify(joinPoint).proceed();
        inOrder.verify(requestContextHolder).clear();
        inOrder.verify(mdcWrapper).remove(MDCWrapper.SESSION_ID_MDC_KEY);
    }

    @Test
    void shouldPopulateRequestContextWithEmptyRequestId() throws Throwable {
        givenJoinPointHasMethodSignature();
        given(requestContextHolder.get()).willReturn(requestContext);

        Object result = advisor.updateRequestContext(joinPoint);

        assertThat(result).isEqualTo(RETURN_VALUE);
        InOrder inOrder = inOrder(joinPoint, requestContext, mdcWrapper, requestContextHolder);
        inOrder.verify(requestContext).setSessionId(SESSION_ID);
        inOrder.verify(mdcWrapper).put(MDCWrapper.REQUEST_ID_MDC_KEY, "");
        inOrder.verify(joinPoint).proceed();
        inOrder.verify(requestContextHolder).clear();
        inOrder.verify(mdcWrapper).remove(MDCWrapper.REQUEST_ID_MDC_KEY);
    }

    @Test
    void shouldClearRequestContextAndMDCAfterException() throws Throwable {
        givenJoinPointHasMethodSignature();
        given(requestContextHolder.get()).willReturn(requestContext);
        RuntimeException exception = new RuntimeException();
        given(joinPoint.proceed()).willThrow(exception);

        Throwable thrown = catchThrowable(() -> advisor.updateRequestContext(joinPoint));

        assertThat(thrown).isEqualTo(exception);
        InOrder inOrder = inOrder(joinPoint, requestContext, mdcWrapper, requestContextHolder);
        inOrder.verify(joinPoint).proceed();
        inOrder.verify(requestContextHolder).clear();
        inOrder.verify(mdcWrapper).remove(MDCWrapper.REQUEST_ID_MDC_KEY);
        inOrder.verify(mdcWrapper).remove(MDCWrapper.SESSION_ID_MDC_KEY);
    }

    private void givenJoinPointHasMethodSignature() throws Throwable {
        given(joinPoint.getSignature()).willReturn(methodSignature);
        given(joinPoint.proceed()).willReturn(RETURN_VALUE);
        given(methodSignature.getMethod()).willReturn(method);
    }

    @NewRequestContextWithSessionId(sessionId = SESSION_ID)
    public void annotatedMethod() {
        // nothing to do :)
    }

    private Method getAnnotatedMethod() {
        try {
            return this.getClass().getMethod("annotatedMethod");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unable to get reference to method", e);
        }
    }
}
package uk.gov.dhsc.htbhf.requestcontext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;
import static uk.gov.dhsc.htbhf.requestcontext.MDCWrapper.REQUEST_ID_MDC_KEY;
import static uk.gov.dhsc.htbhf.requestcontext.MDCWrapper.SESSION_ID_MDC_KEY;
import static uk.gov.dhsc.htbhf.requestcontext.RequestIdFilter.REQUEST_ID_HEADER;
import static uk.gov.dhsc.htbhf.requestcontext.RequestIdFilter.SESSION_ID_HEADER;

@ExtendWith(MockitoExtension.class)
class RequestIdFilterTest {

    @Mock
    RequestContextHolder requestContextHolder;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    FilterChain filterChain;
    @Mock
    MDCWrapper mdcWrapper;
    @Mock
    RequestContext requestContext;

    @InjectMocks
    RequestIdFilter filter;

    @Test
    void shouldAssignRequestIdToMDCAndRequestContext() throws Exception {
        // Given
        String requestId = "MyRequestId";
        given(request.getHeader(REQUEST_ID_HEADER)).willReturn(requestId);
        given(requestContextHolder.get()).willReturn(requestContext);

        // When
        filter.doFilterInternal(request, response, filterChain);

        // Then
        InOrder inOrder = Mockito.inOrder(mdcWrapper, requestContextHolder, filterChain, requestContext);

        inOrder.verify(mdcWrapper).put(REQUEST_ID_MDC_KEY, requestId);
        inOrder.verify(requestContext).setRequestId(requestId);
        inOrder.verify(filterChain).doFilter(request, response);
        inOrder.verify(mdcWrapper).clear();
        inOrder.verify(requestContextHolder).clear();
    }

    @Test
    void shouldAssignSessionIdToMDCAndRequestContext() throws Exception {
        // Given
        String sessionId = "MySessionId";
        willReturn("stop mockito reporting strict stubbing argument mismatch.").given(request).getHeader(ArgumentMatchers.anyString());
        willReturn(sessionId).given(request).getHeader(SESSION_ID_HEADER);
        given(requestContextHolder.get()).willReturn(requestContext);

        // When
        filter.doFilterInternal(request, response, filterChain);

        // Then
        InOrder inOrder = Mockito.inOrder(mdcWrapper, requestContextHolder, filterChain, requestContext);

        inOrder.verify(mdcWrapper).put(SESSION_ID_MDC_KEY, sessionId);
        inOrder.verify(requestContext).setSessionId(sessionId);
        inOrder.verify(filterChain).doFilter(request, response);
        inOrder.verify(mdcWrapper).clear();
        inOrder.verify(requestContextHolder).clear();
    }

    @Test
    void shouldCreateRequestIdWhenNoneProvided() throws Exception {
        // Given
        given(requestContextHolder.get()).willReturn(requestContext);

        // When
        filter.doFilterInternal(request, response, filterChain);

        // Then
        InOrder inOrder = Mockito.inOrder(mdcWrapper, requestContextHolder, filterChain, requestContext);

        inOrder.verify(mdcWrapper).put(ArgumentMatchers.eq(REQUEST_ID_MDC_KEY), ArgumentMatchers.anyString());
        inOrder.verify(requestContext).setRequestId(ArgumentMatchers.anyString());
        inOrder.verify(filterChain).doFilter(request, response);
        inOrder.verify(mdcWrapper).clear();
        inOrder.verify(requestContextHolder).clear();
    }

    @Test
    void shouldPopulateMethodAndServletPathOfRequestContext() throws Exception {
        // Given
        String method = "POST";
        given(request.getMethod()).willReturn(method);
        String servletPath = "/my/path";
        given(request.getServletPath()).willReturn(servletPath);
        given(requestContextHolder.get()).willReturn(requestContext);

        // When
        filter.doFilterInternal(request, response, filterChain);

        // Then
        verify(requestContext).setMethod(method);
        verify(requestContext).setServletPath(servletPath);
    }

    @Test
    void shouldClearMDCAndRequestContextAfterException() throws Exception {
        // Given
        RuntimeException exception = new RuntimeException("Unexpected Error");
        willThrow(exception).given(filterChain).doFilter(request, response);
        given(requestContextHolder.get()).willReturn(requestContext);

        // When
        Throwable thrown = catchThrowable(() -> filter.doFilterInternal(request, response, filterChain));

        // Then
        assertThat(thrown).isEqualTo(exception);

        InOrder inOrder = Mockito.inOrder(mdcWrapper, requestContextHolder, filterChain, requestContext);
        inOrder.verify(mdcWrapper).put(ArgumentMatchers.eq(REQUEST_ID_MDC_KEY), ArgumentMatchers.anyString());
        inOrder.verify(requestContext).setRequestId(ArgumentMatchers.anyString());
        inOrder.verify(filterChain).doFilter(request, response);
        inOrder.verify(mdcWrapper).clear();
        inOrder.verify(requestContextHolder).clear();
    }

}

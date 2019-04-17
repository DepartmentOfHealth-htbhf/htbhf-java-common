package uk.gov.dhsc.htbhf.requestcontext;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RequestContextHolderTest {

    @Test
    void shouldInitialiseARequestContext() {
        RequestContextHolder holder = new RequestContextHolder();

        RequestContext requestContext = holder.get();

        assertThat(requestContext).isNotNull();
    }

    @Test
    void shouldClearRequestContextAndReplaceWithNewRequestContext() {
        RequestContextHolder holder = new RequestContextHolder();
        RequestContext requestContext = holder.get();
        requestContext.setServletPath("servletPath");
        requestContext.setMethod("method");
        requestContext.setRequestId("requestId");
        requestContext.setSessionId("sessionId");

        holder.clear();

        assertThat(holder.get()).isNotNull();
        RequestContext newRequestContext = holder.get();
        assertThat(newRequestContext.getSessionId()).isNull();
        assertThat(newRequestContext.getSessionId()).isNull();
        assertThat(newRequestContext.getSessionId()).isNull();
        assertThat(newRequestContext.getSessionId()).isNull();
    }

}

package uk.gov.dhsc.htbhf.requestcontext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

/**
 * Configuration class to be used to setup the required beans to setup the request scoped
 * request context and the filter to be used to add those values to the MDC for logging.
 */
@Configuration
public class RequestContextConfiguration {

    @Bean
    public MDCWrapper mdcWrapper() {
        return new MDCWrapper();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RequestContext requestContext() {
        return new RequestContext();
    }

    @Bean
    public RequestIdFilter requestIdFilter() {
        return new RequestIdFilter(requestContext(), mdcWrapper());
    }
}

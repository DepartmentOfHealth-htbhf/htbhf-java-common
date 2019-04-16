package uk.gov.dhsc.htbhf.requestcontext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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
    public RequestContext requestContext() {
        return new RequestContext();
    }

    @Bean
    public RequestIdFilter requestIdFilter() {
        return new RequestIdFilter(requestContext(), mdcWrapper());
    }

    @Bean(name = "restTemplateWithIdHeaders")
    public RestTemplate restTemplateWithIdHeaders() {
        var restTemplate = new RestTemplate();
        var interceptors = restTemplate.getInterceptors();
        interceptors.add(headerInterceptor());
        return restTemplate;
    }

    @Bean
    public HeaderInterceptor headerInterceptor() {
        return new HeaderInterceptor(requestContext());
    }
}

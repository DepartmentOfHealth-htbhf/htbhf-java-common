package uk.gov.dhsc.htbhf.requestcontext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import uk.gov.dhsc.htbhf.requestcontext.aop.RequestContextAdvisor;

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
    public RequestContextHolder requestContextHolder() {
        return new RequestContextHolder();
    }

    @Bean
    public RequestIdFilter requestIdFilter() {
        return new RequestIdFilter(requestContextHolder(), mdcWrapper());
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
        return new HeaderInterceptor(requestContextHolder());
    }

    @Bean
    public RequestContextAdvisor requestContextAdvisor() {
        return new RequestContextAdvisor(requestContextHolder(), mdcWrapper());
    }
}

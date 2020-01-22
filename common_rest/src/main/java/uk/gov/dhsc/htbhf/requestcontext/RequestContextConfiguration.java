package uk.gov.dhsc.htbhf.requestcontext;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import uk.gov.dhsc.htbhf.requestcontext.aop.RequestContextAdvisor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static uk.gov.dhsc.htbhf.ObjectMapperFactory.configureObjectMapper;

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
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(replaceMappingJackson2HttpMessageConverter(restTemplate.getMessageConverters()));
        restTemplate.getInterceptors().add(headerInterceptor());
        return restTemplate;
    }

    private List<HttpMessageConverter<?>> replaceMappingJackson2HttpMessageConverter(List<HttpMessageConverter<?>> originalConverters) {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(mappingJackson2HttpMessageConverter());
        converters.addAll(originalConverters.stream()
                .filter(converter -> !(converter instanceof MappingJackson2HttpMessageConverter))
                .collect(Collectors.toList())
        );
        return converters;
    }

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    public ObjectMapper objectMapper() {
        // This ObjectMapper is not spring-managed, but we want to give it the same settings
        // see also: https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-customize-the-jackson-objectmapper
        return configureObjectMapper();
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
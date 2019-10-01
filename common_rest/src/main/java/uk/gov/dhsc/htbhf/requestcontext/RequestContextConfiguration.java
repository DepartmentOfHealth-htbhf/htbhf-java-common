package uk.gov.dhsc.htbhf.requestcontext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import uk.gov.dhsc.htbhf.requestcontext.aop.RequestContextAdvisor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Bean
    public ObjectMapper objectMapper() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE));

        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        objectMapper.registerModule(javaTimeModule);

        return objectMapper;
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

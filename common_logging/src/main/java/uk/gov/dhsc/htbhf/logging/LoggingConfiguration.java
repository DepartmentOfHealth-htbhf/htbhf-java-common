package uk.gov.dhsc.htbhf.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Configuration that can be used to setup the {@link EventLogger}. The {@link ObjectMapper} created in this configuration
 * is specifically created as a private instance so that it doesn't clash with any ObjectMapper in the application context
 * this configuration is used in.
 */
@Configuration
public class LoggingConfiguration {

    @Bean
    public EventLogger eventLogger() {
        return new EventLogger(eventLoggingObjectMapper());
    }

    private ObjectMapper eventLoggingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }
}

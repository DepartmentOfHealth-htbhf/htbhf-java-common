package uk.gov.dhsc.htbhf;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ObjectMapperFactory {

    /**
     * Configures an object mapper to use ISO local date and time formats and ignores unknown properties in json object.
     * @return a configured object mapper
     */
    public static ObjectMapper configureObjectMapper() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE));

        JsonFactory factory = JsonFactory.builder()
                // Change per-factory setting to prevent use of `String.intern()` on symbols
                .disable(JsonFactory.Feature.INTERN_FIELD_NAMES)
                .build();

        return JsonMapper.builder(factory)
                .configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .build()
                .findAndRegisterModules()
                .registerModule(javaTimeModule);
    }
}
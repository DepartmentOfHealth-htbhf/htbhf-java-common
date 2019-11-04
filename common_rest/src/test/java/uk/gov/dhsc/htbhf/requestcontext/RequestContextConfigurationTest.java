package uk.gov.dhsc.htbhf.requestcontext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RequestContextConfigurationTest {

    @InjectMocks
    RequestContextConfiguration config;

    @Test
    void shouldConfigureObjectMapperToSerialiseDatesCorrectly() throws JsonProcessingException {
        ObjectMapper objectMapper = config.objectMapper();
        ObjectWithDates obj = ObjectWithDates.builder()
                .date(LocalDate.of(1970, 1, 31))
                .dateTime(LocalDateTime.of(1971, 12, 12, 11, 59))
                .build();

        String result = objectMapper.writeValueAsString(obj);

        assertThat(result).contains("1970-01-31");
        assertThat(result).contains("1971-12-12T11:59:00");

    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class ObjectWithDates {
        LocalDate date;
        LocalDateTime dateTime;
    }
}
package uk.gov.dhsc.htbhf.dwp.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class QualifyingReasonTest {

    @ParameterizedTest
    @CsvSource({
            "UNDER_18, under_18",
            "NOT_SET, not_set"
    })
    void shouldGetResponseValue(QualifyingReason qualifyingReason, String expectedResponseValue) {
        assertThat(qualifyingReason.getResponseValue()).isEqualTo(expectedResponseValue);
    }

}

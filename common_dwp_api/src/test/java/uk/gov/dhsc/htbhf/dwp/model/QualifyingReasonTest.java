package uk.gov.dhsc.htbhf.dwp.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class QualifyingReasonTest {

    @ParameterizedTest
    @CsvSource({
            "UNIVERSAL_CREDIT, universal_credit",
            "EMPLOYMENT_AND_SUPPORT_ALLOWANCE, employment_and_support_allowance",
            "INCOME_SUPPORT, income_support",
            "JOBSEEKERS_ALLOWANCE, jobseekers_allowance",
            "PENSION_CREDIT, pension_credit",
            "CHILD_TAX_CREDIT, child_tax_credit",
            "UNDER_18, under_18",
            "NOT_SET, not_set"
    })
    void shouldGetResponseValue(QualifyingReason qualifyingReason, String expectedResponseValue) {
        assertThat(qualifyingReason.getResponseValue()).isEqualTo(expectedResponseValue);
    }
}

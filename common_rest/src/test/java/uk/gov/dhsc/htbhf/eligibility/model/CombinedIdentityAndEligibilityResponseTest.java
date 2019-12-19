package uk.gov.dhsc.htbhf.eligibility.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.gov.dhsc.htbhf.dwp.model.VerificationOutcome.*;
import static uk.gov.dhsc.htbhf.eligibility.model.CombinedIdentityAndEligibilityResponse.builder;
import static uk.gov.dhsc.htbhf.eligibility.model.testhelper.CombinedIdAndEligibilityResponseTestDataFactory.anIdMatchFailedResponse;
import static uk.gov.dhsc.htbhf.eligibility.model.testhelper.CombinedIdAndEligibilityResponseTestDataFactory.anIdMatchedEligibilityConfirmedUCResponseWithAllMatches;
import static uk.gov.dhsc.htbhf.eligibility.model.testhelper.CombinedIdAndEligibilityResponseTestDataFactory.anIdMatchedEligibilityNotConfirmedResponse;

class CombinedIdentityAndEligibilityResponseTest {

    @Test
    void shouldNotBeIneligibleForConfirmedEligibilityOutcome() {
        CombinedIdentityAndEligibilityResponse response = anIdMatchedEligibilityConfirmedUCResponseWithAllMatches();
        assertThat(response.isNotEligible()).isFalse();
    }

    @Test
    void shouldBeEligibleForConfirmedEligibilityOutcome() {
        CombinedIdentityAndEligibilityResponse response = anIdMatchedEligibilityConfirmedUCResponseWithAllMatches();
        assertThat(response.isEligible()).isTrue();
    }

    @Test
    void shouldBeIneligibleForNotSetEligibilityOutcome() {
        CombinedIdentityAndEligibilityResponse response = anIdMatchFailedResponse();
        assertThat(response.isNotEligible()).isTrue();
    }

    @Test
    void shouldNotBeEligibleForNotSetEligibilityOutcome() {
        CombinedIdentityAndEligibilityResponse response = anIdMatchFailedResponse();
        assertThat(response.isEligible()).isFalse();
    }

    @Test
    void shouldBeIneligibleForNotConfirmedEligibilityOutcome() {
        CombinedIdentityAndEligibilityResponse response = anIdMatchedEligibilityNotConfirmedResponse();
        assertThat(response.isNotEligible()).isTrue();
    }

    @Test
    void shouldNotBeEligibleForNotConfirmedEligibilityOutcome() {
        CombinedIdentityAndEligibilityResponse response = anIdMatchedEligibilityNotConfirmedResponse();
        assertThat(response.isEligible()).isFalse();
    }

    @Test
    void shouldBeIneligibleEligibilityOutcomeNotSet() {
        CombinedIdentityAndEligibilityResponse response = builder().build();
        assertThat(response.isNotEligible()).isTrue();
    }

    @Test
    void shouldNotBeEligibleEligibilityOutcomeNotSet() {
        CombinedIdentityAndEligibilityResponse response = builder().build();
        assertThat(response.isEligible()).isFalse();
    }

    @Test
    void shouldReportEmailAndPhoneMatched() {
        CombinedIdentityAndEligibilityResponse response = builder()
                        .emailAddressMatch(MATCHED)
                        .mobilePhoneMatch(MATCHED)
                        .build();

        assertThat(response.isEmailAndPhoneMatched()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("responsesWithEmailOrPhoneNotMatched")
    void shouldReportEmailOrPhoneNotMatched(CombinedIdentityAndEligibilityResponse response) {
        assertThat(response.isEmailAndPhoneMatched()).isFalse();
    }

    private static Stream<Arguments> responsesWithEmailOrPhoneNotMatched() {
        return Stream.of(
                Arguments.of(builder().mobilePhoneMatch(MATCHED).emailAddressMatch(INVALID_FORMAT).build()),
                Arguments.of(builder().mobilePhoneMatch(MATCHED).emailAddressMatch(NOT_HELD).build()),
                Arguments.of(builder().mobilePhoneMatch(MATCHED).emailAddressMatch(NOT_MATCHED).build()),
                Arguments.of(builder().mobilePhoneMatch(MATCHED).emailAddressMatch(NOT_SET).build()),
                Arguments.of(builder().mobilePhoneMatch(MATCHED).emailAddressMatch(NOT_SUPPLIED).build()),

                Arguments.of(builder().mobilePhoneMatch(INVALID_FORMAT).emailAddressMatch(MATCHED).build()),
                Arguments.of(builder().mobilePhoneMatch(NOT_HELD).emailAddressMatch(MATCHED).build()),
                Arguments.of(builder().mobilePhoneMatch(NOT_MATCHED).emailAddressMatch(MATCHED).build()),
                Arguments.of(builder().mobilePhoneMatch(NOT_SET).emailAddressMatch(MATCHED).build()),
                Arguments.of(builder().mobilePhoneMatch(NOT_SUPPLIED).emailAddressMatch(MATCHED).build()),

                Arguments.of(builder().mobilePhoneMatch(INVALID_FORMAT).emailAddressMatch(INVALID_FORMAT).build()),
                Arguments.of(builder().mobilePhoneMatch(NOT_HELD).emailAddressMatch(NOT_HELD).build()),
                Arguments.of(builder().mobilePhoneMatch(NOT_MATCHED).emailAddressMatch(NOT_MATCHED).build()),
                Arguments.of(builder().mobilePhoneMatch(NOT_SET).emailAddressMatch(NOT_SET).build()),
                Arguments.of(builder().mobilePhoneMatch(NOT_SUPPLIED).emailAddressMatch(NOT_SUPPLIED).build()),

                Arguments.of(builder().build())
        );
    }

}

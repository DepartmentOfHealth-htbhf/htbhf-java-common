package uk.gov.dhsc.htbhf.eligibility.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
        CombinedIdentityAndEligibilityResponse response = CombinedIdentityAndEligibilityResponse.builder().build();
        assertThat(response.isNotEligible()).isTrue();
    }

    @Test
    void shouldNotBeEligibleEligibilityOutcomeNotSet() {
        CombinedIdentityAndEligibilityResponse response = CombinedIdentityAndEligibilityResponse.builder().build();
        assertThat(response.isEligible()).isFalse();
    }

}

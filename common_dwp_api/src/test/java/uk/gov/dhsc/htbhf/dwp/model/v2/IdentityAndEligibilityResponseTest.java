package uk.gov.dhsc.htbhf.dwp.model.v2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.gov.dhsc.htbhf.dwp.testhelper.v2.IdAndEligibilityResponseTestDataFactory.anIdMatchFailedResponse;
import static uk.gov.dhsc.htbhf.dwp.testhelper.v2.IdAndEligibilityResponseTestDataFactory.anIdMatchedEligibilityConfirmedUCResponseWithAllMatches;
import static uk.gov.dhsc.htbhf.dwp.testhelper.v2.IdAndEligibilityResponseTestDataFactory.anIdMatchedEligibilityNotConfirmedResponse;

class IdentityAndEligibilityResponseTest {

    @Test
    void shouldNotBeIneligibleForConfirmedEligibilityOutcome() {
        IdentityAndEligibilityResponse response = anIdMatchedEligibilityConfirmedUCResponseWithAllMatches();
        assertThat(response.isNotEligible()).isFalse();
    }

    @Test
    void shouldBeEligibleForConfirmedEligibilityOutcome() {
        IdentityAndEligibilityResponse response = anIdMatchedEligibilityConfirmedUCResponseWithAllMatches();
        assertThat(response.isEligible()).isTrue();
    }

    @Test
    void shouldBeIneligibleForNotSetEligibilityOutcome() {
        IdentityAndEligibilityResponse response = anIdMatchFailedResponse();
        assertThat(response.isNotEligible()).isTrue();
    }

    @Test
    void shouldNotBeEligibleForNotSetEligibilityOutcome() {
        IdentityAndEligibilityResponse response = anIdMatchFailedResponse();
        assertThat(response.isEligible()).isFalse();
    }

    @Test
    void shouldBeIneligibleForNotConfirmedEligibilityOutcome() {
        IdentityAndEligibilityResponse response = anIdMatchedEligibilityNotConfirmedResponse();
        assertThat(response.isNotEligible()).isTrue();
    }

    @Test
    void shouldNotBeEligibleForNotConfirmedEligibilityOutcome() {
        IdentityAndEligibilityResponse response = anIdMatchedEligibilityNotConfirmedResponse();
        assertThat(response.isEligible()).isFalse();
    }

    @Test
    void shouldBeIneligibleEligibilityOutcomeNotSet() {
        IdentityAndEligibilityResponse response = IdentityAndEligibilityResponse.builder().build();
        assertThat(response.isNotEligible()).isTrue();
    }

    @Test
    void shouldNotBeEligibleEligibilityOutcomeNotSet() {
        IdentityAndEligibilityResponse response = IdentityAndEligibilityResponse.builder().build();
        assertThat(response.isEligible()).isFalse();
    }
}

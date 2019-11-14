package uk.gov.dhsc.htbhf.dwp.model.v2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.gov.dhsc.htbhf.dwp.testhelper.v2.IdentityAndEligibilityResponseTestDataFactory.anIdentityMatchFailedResponse;
import static uk.gov.dhsc.htbhf.dwp.testhelper.v2.IdentityAndEligibilityResponseTestDataFactory.anIdentityMatchedEligibilityConfirmedUCResponseWithAllMatches;
import static uk.gov.dhsc.htbhf.dwp.testhelper.v2.IdentityAndEligibilityResponseTestDataFactory.anIdentityMatchedEligibilityNotConfirmedResponse;

class IdentityAndEligibilityResponseTest {

    @Test
    void shouldBeEligibleForConfirmedEligibilityOutcome() {
        IdentityAndEligibilityResponse response = anIdentityMatchedEligibilityConfirmedUCResponseWithAllMatches();
        assertThat(response.isNotEligible()).isFalse();
    }

    @Test
    void shouldBeIneligibleForNotSetEligibilityOutcome() {
        IdentityAndEligibilityResponse response = anIdentityMatchFailedResponse();
        assertThat(response.isNotEligible()).isTrue();
    }

    @Test
    void shouldBeIneligibleForNotConfirmedEligibilityOutcome() {
        IdentityAndEligibilityResponse response = anIdentityMatchedEligibilityNotConfirmedResponse();
        assertThat(response.isNotEligible()).isTrue();
    }

    @Test
    void shouldBeIneligibleEligibilityOutcomeNotSet() {
        IdentityAndEligibilityResponse response = IdentityAndEligibilityResponse.builder().build();
        assertThat(response.isNotEligible()).isTrue();
    }
}

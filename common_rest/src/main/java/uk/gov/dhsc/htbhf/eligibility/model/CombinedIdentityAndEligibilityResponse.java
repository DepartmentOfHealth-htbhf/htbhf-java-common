package uk.gov.dhsc.htbhf.eligibility.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import uk.gov.dhsc.htbhf.dwp.model.*;

import java.time.LocalDate;
import java.util.List;

/**
 * The combined identity and eligibility response data from DWP and HMRC.
 */

@Value
@Builder(toBuilder = true)
@AllArgsConstructor(onConstructor_ = {@JsonCreator})
public class CombinedIdentityAndEligibilityResponse {

    @JsonProperty("identityStatus")
    private final IdentityOutcome identityStatus;

    @JsonProperty("eligibilityStatus")
    private final EligibilityOutcome eligibilityStatus;

    @JsonProperty("deathVerificationFlag")
    private final DeathVerificationFlag deathVerificationFlag;

    @JsonProperty("mobilePhoneMatch")
    private final VerificationOutcome mobilePhoneMatch;

    @JsonProperty("emailAddressMatch")
    private final VerificationOutcome emailAddressMatch;

    @JsonProperty("addressLine1Match")
    private final VerificationOutcome addressLine1Match;

    @JsonProperty("postcodeMatch")
    private final VerificationOutcome postcodeMatch;

    @JsonProperty("pregnantChildDOBMatch")
    private final VerificationOutcome pregnantChildDOBMatch;

    @JsonProperty("qualifyingReason")
    private final QualifyingReason qualifyingReason;

    @JsonProperty("dwpHouseholdIdentifier")
    private final String dwpHouseholdIdentifier;

    @JsonProperty("hmrcHouseholdIdentifier")
    private final String hmrcHouseholdIdentifier;

    @JsonProperty("dobOfChildrenUnder4")
    private final List<LocalDate> dobOfChildrenUnder4;

    /**
     * Determine whether the eligibility outcome is considered not eligible.
     *
     * @return Whether the eligibility outcome is considered not eligible.
     */
    @JsonIgnore
    public boolean isNotEligible() {
        return !isEligible();
    }

    /**
     * Determine whether the eligibility outcome is considered eligible.
     *
     * @return Whether the eligibility outcome is considered eligible.
     */
    @JsonIgnore
    public boolean isEligible() {
        return EligibilityOutcome.CONFIRMED == eligibilityStatus;
    }

    /**
     * Determine whether the email and mobile phone are both matched.
     *
     * @return true if both mobilePhoneMatch and emailAddressMatch are {@link VerificationOutcome#MATCHED}, false otherwise.
     */
    @JsonIgnore
    public boolean isEmailAndPhoneMatched() {
        return mobilePhoneMatch == VerificationOutcome.MATCHED && emailAddressMatch == VerificationOutcome.MATCHED;
    }

}

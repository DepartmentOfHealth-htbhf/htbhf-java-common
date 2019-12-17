package uk.gov.dhsc.htbhf.dwp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

/**
 * Response object containing the result of the identity matching and eligibility process.
 */
@Value
@Builder(toBuilder = true)
@AllArgsConstructor(onConstructor_ = {@JsonCreator})
public class IdentityAndEligibilityResponse {

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

    @JsonProperty("qualifyingBenefits")
    private final QualifyingBenefits qualifyingBenefits;

    @JsonProperty("householdIdentifier")
    private final String householdIdentifier;

    @JsonProperty("dobOfChildrenUnder4")
    private final List<LocalDate> dobOfChildrenUnder4;

    @JsonProperty("identityStatus")
    private final IdentityOutcome identityStatus;

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
     * Determine whether the address is fully matched or not.
     *
     * @return Whether the address is fully matched or not.
     */
    @JsonIgnore
    public boolean isAddressMatched() {
        return VerificationOutcome.MATCHED == addressLine1Match && VerificationOutcome.MATCHED == postcodeMatch;
    }

    /**
     * Determine whether the address is mismatched or not.
     *
     * @return Whether the address is mismatched or not.
     */
    @JsonIgnore
    public boolean isAddressMismatch() {
        return !isAddressMatched();
    }
}

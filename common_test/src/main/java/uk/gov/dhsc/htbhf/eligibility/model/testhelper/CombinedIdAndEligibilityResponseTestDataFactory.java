package uk.gov.dhsc.htbhf.eligibility.model.testhelper;

import uk.gov.dhsc.htbhf.dwp.model.*;
import uk.gov.dhsc.htbhf.eligibility.model.CombinedIdentityAndEligibilityResponse;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;
import static uk.gov.dhsc.htbhf.TestConstants.*;

@SuppressWarnings("PMD.TooManyMethods")
public class CombinedIdAndEligibilityResponseTestDataFactory {

    public static CombinedIdentityAndEligibilityResponse anIdMatchFailedResponse() {
        return defaultBuilderWithIdentityNotMatchedValues().build();
    }

    public static CombinedIdentityAndEligibilityResponse anIdMatchedEligibilityNotConfirmedResponse() {
        return CombinedIdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.MATCHED)
                .eligibilityStatus(EligibilityOutcome.NOT_CONFIRMED)
                .addressLine1Match(VerificationOutcome.NOT_SET)
                .postcodeMatch(VerificationOutcome.NOT_SET)
                .mobilePhoneMatch(VerificationOutcome.NOT_SET)
                .emailAddressMatch(VerificationOutcome.NOT_SET)
                .qualifyingBenefits(QualifyingBenefits.NOT_SET)
                .dwpHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .hmrcHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .dobOfChildrenUnder4(emptyList())
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SET)
                .deathVerificationFlag(DeathVerificationFlag.N_A)
                .build();
    }

    public static CombinedIdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedPostcodeNotMatchedResponse() {
        return CombinedIdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.MATCHED)
                .eligibilityStatus(EligibilityOutcome.CONFIRMED)
                .addressLine1Match(VerificationOutcome.MATCHED)
                .postcodeMatch(VerificationOutcome.NOT_MATCHED)
                .qualifyingBenefits(QualifyingBenefits.UNIVERSAL_CREDIT)
                .mobilePhoneMatch(VerificationOutcome.MATCHED)
                .emailAddressMatch(VerificationOutcome.MATCHED)
                .dwpHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .hmrcHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .dobOfChildrenUnder4(MAGGIE_AND_LISA_DOBS)
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SUPPLIED)
                .deathVerificationFlag(DeathVerificationFlag.N_A)
                .build();
    }

    public static CombinedIdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedAddressNotMatchedResponse() {
        return CombinedIdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.MATCHED)
                .eligibilityStatus(EligibilityOutcome.CONFIRMED)
                .addressLine1Match(VerificationOutcome.NOT_MATCHED)
                .postcodeMatch(VerificationOutcome.MATCHED)
                .mobilePhoneMatch(VerificationOutcome.MATCHED)
                .emailAddressMatch(VerificationOutcome.MATCHED)
                .qualifyingBenefits(QualifyingBenefits.UNIVERSAL_CREDIT)
                .dwpHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .hmrcHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .dobOfChildrenUnder4(MAGGIE_AND_LISA_DOBS)
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SUPPLIED)
                .deathVerificationFlag(DeathVerificationFlag.N_A)
                .build();
    }

    public static CombinedIdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedFullAddressNotMatchedResponse() {
        return CombinedIdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.MATCHED)
                .eligibilityStatus(EligibilityOutcome.CONFIRMED)
                .addressLine1Match(VerificationOutcome.NOT_MATCHED)
                .postcodeMatch(VerificationOutcome.NOT_MATCHED)
                .mobilePhoneMatch(VerificationOutcome.MATCHED)
                .emailAddressMatch(VerificationOutcome.MATCHED)
                .qualifyingBenefits(QualifyingBenefits.UNIVERSAL_CREDIT)
                .dwpHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .hmrcHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .dobOfChildrenUnder4(MAGGIE_AND_LISA_DOBS)
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SUPPLIED)
                .deathVerificationFlag(DeathVerificationFlag.N_A)
                .build();
    }

    public static CombinedIdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedEmailNotMatchedResponse() {
        return anIdMatchedEligibilityConfirmedUCResponseWithMatches(VerificationOutcome.MATCHED, VerificationOutcome.NOT_MATCHED, MAGGIE_AND_LISA_DOBS);
    }

    public static CombinedIdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedPhoneNotMatchedResponse() {
        return anIdMatchedEligibilityConfirmedUCResponseWithMatches(VerificationOutcome.NOT_MATCHED, VerificationOutcome.MATCHED, MAGGIE_AND_LISA_DOBS);
    }

    public static CombinedIdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedEmailAndPhoneNotMatchedResponse() {
        return anIdMatchedEligibilityConfirmedUCResponseWithMatches(VerificationOutcome.NOT_MATCHED, VerificationOutcome.NOT_MATCHED, MAGGIE_AND_LISA_DOBS);
    }

    public static CombinedIdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedUCResponseWithMatches(VerificationOutcome mobileVerification,
                                                                                                              VerificationOutcome emailVerification,
                                                                                                              List<LocalDate> childrenDobs) {
        return CombinedIdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.MATCHED)
                .eligibilityStatus(EligibilityOutcome.CONFIRMED)
                .addressLine1Match(VerificationOutcome.MATCHED)
                .postcodeMatch(VerificationOutcome.MATCHED)
                .mobilePhoneMatch(mobileVerification)
                .emailAddressMatch(emailVerification)
                .qualifyingBenefits(QualifyingBenefits.UNIVERSAL_CREDIT)
                .dwpHouseholdIdentifier(DWP_HOUSEHOLD_IDENTIFIER)
                .hmrcHouseholdIdentifier(HMRC_HOUSEHOLD_IDENTIFIER)
                .dobOfChildrenUnder4(childrenDobs)
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SUPPLIED)
                .deathVerificationFlag(DeathVerificationFlag.N_A)
                .build();
    }

    public static CombinedIdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedUCResponseWithAllMatchesAndDwpHouseIdentifier(String dwpIdentifier) {
        return defaultBuilderWithEligibilityConfirmedUCResponseWithAllMatches()
                .dwpHouseholdIdentifier(dwpIdentifier)
                .build();
    }

    public static CombinedIdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedUCResponseWithAllMatchesAndHmrcHouseIdentifier(String hmrcIdentifier) {
        return defaultBuilderWithEligibilityConfirmedUCResponseWithAllMatches()
                .hmrcHouseholdIdentifier(hmrcIdentifier)
                .build();
    }

    public static CombinedIdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedUCResponseWithAllMatches() {
        return defaultBuilderWithEligibilityConfirmedUCResponseWithAllMatches().build();
    }

    public static CombinedIdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedUCResponseWithAllMatches(List<LocalDate> childrenDobs) {
        return defaultBuilderWithEligibilityConfirmedUCResponseWithAllMatches()
                .dobOfChildrenUnder4(childrenDobs)
                .build();
    }

    /**
     * Creates a {@link CombinedIdentityAndEligibilityResponse} with values based on the passed in eligibility outcome.
     * An eligibility outcome of CONFIRMED will create a response with id matched and all other matches set to CONFIRMED,
     * NOT_CONFIRMED will create a response with id matched and all other matches set to NOT_CONFIRMED,
     * NOT_SET will create a response with id not matched and all other matches set to NOT_SET.
     * Note, the children's date of births are only set if the eligibility outcome is CONFIRMED.
     *
     * @param childrenDobs       the children's dates of birth
     * @param eligibilityOutcome the eligibility outcome
     * @return a {@link CombinedIdentityAndEligibilityResponse}
     */
    public static CombinedIdentityAndEligibilityResponse aCombinedIdentityAndEligibilityResponse(List<LocalDate> childrenDobs,
                                                                                                 EligibilityOutcome eligibilityOutcome) {
        switch (eligibilityOutcome) {
            case CONFIRMED:
                return anIdMatchedEligibilityConfirmedUCResponseWithAllMatches(childrenDobs);
            case NOT_CONFIRMED:
                return anIdMatchedEligibilityNotConfirmedResponse();
            case NOT_SET:
                return anIdMatchFailedResponse();
            default:
                throw new IllegalArgumentException("No response defined for eligibility outcome: " + eligibilityOutcome.name());
        }
    }

    public static CombinedIdentityAndEligibilityResponse aCombinedIdentityAndEligibilityResponseWithOverride(
            EligibilityOutcome overrideEligibilityStatus,
            List<LocalDate> childrenDob) {
        VerificationOutcome matchOutcome = overrideEligibilityStatus == EligibilityOutcome.CONFIRMED
                ? VerificationOutcome.MATCHED
                : VerificationOutcome.NOT_SET;
        return getCombinedIdentityAndEligibilityResponseWithOverride(overrideEligibilityStatus, childrenDob, matchOutcome).build();
    }

    public static CombinedIdentityAndEligibilityResponse aCombinedIdentityAndEligibilityResponseWithOverrideUnder18AndNoChildren() {
        return getCombinedIdentityAndEligibilityResponseWithOverride(EligibilityOutcome.CONFIRMED, NO_CHILDREN, VerificationOutcome.MATCHED)
                .qualifyingReason(QualifyingReason.UNDER_18).build();
    }

    private static CombinedIdentityAndEligibilityResponse.CombinedIdentityAndEligibilityResponseBuilder getCombinedIdentityAndEligibilityResponseWithOverride(
            EligibilityOutcome overrideEligibilityStatus,
            List<LocalDate> childrenDob, VerificationOutcome matchOutcome) {
        return CombinedIdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.MATCHED)
                .eligibilityStatus(overrideEligibilityStatus)
                .dobOfChildrenUnder4(childrenDob)
                .pregnantChildDOBMatch(matchOutcome)
                .addressLine1Match(matchOutcome)
                .emailAddressMatch(matchOutcome)
                .mobilePhoneMatch(matchOutcome)
                .postcodeMatch(matchOutcome)
                .deathVerificationFlag(DeathVerificationFlag.N_A);
    }

    private static CombinedIdentityAndEligibilityResponse.CombinedIdentityAndEligibilityResponseBuilder defaultBuilderWithIdentityNotMatchedValues() {
        return CombinedIdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.NOT_MATCHED)
                .eligibilityStatus(EligibilityOutcome.NOT_SET)
                .addressLine1Match(VerificationOutcome.NOT_SET)
                .postcodeMatch(VerificationOutcome.NOT_SET)
                .mobilePhoneMatch(VerificationOutcome.NOT_SET)
                .emailAddressMatch(VerificationOutcome.NOT_SET)
                .qualifyingBenefits(QualifyingBenefits.NOT_SET)
                .dwpHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .hmrcHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .dobOfChildrenUnder4(emptyList())
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SET)
                .deathVerificationFlag(DeathVerificationFlag.N_A);
    }

    private static CombinedIdentityAndEligibilityResponse.CombinedIdentityAndEligibilityResponseBuilder
            defaultBuilderWithEligibilityConfirmedUCResponseWithAllMatches() {
        return CombinedIdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.MATCHED)
                .eligibilityStatus(EligibilityOutcome.CONFIRMED)
                .addressLine1Match(VerificationOutcome.MATCHED)
                .postcodeMatch(VerificationOutcome.MATCHED)
                .mobilePhoneMatch(VerificationOutcome.MATCHED)
                .emailAddressMatch(VerificationOutcome.MATCHED)
                .qualifyingBenefits(QualifyingBenefits.UNIVERSAL_CREDIT)
                .dwpHouseholdIdentifier(DWP_HOUSEHOLD_IDENTIFIER)
                .hmrcHouseholdIdentifier(HMRC_HOUSEHOLD_IDENTIFIER)
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SUPPLIED)
                .deathVerificationFlag(DeathVerificationFlag.N_A)
                .dobOfChildrenUnder4(MAGGIE_AND_LISA_DOBS);
    }

}

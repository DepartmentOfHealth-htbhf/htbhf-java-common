package uk.gov.dhsc.htbhf.eligibility.model.testhelper;

import uk.gov.dhsc.htbhf.dwp.model.*;
import uk.gov.dhsc.htbhf.eligibility.model.CombinedIdentityAndEligibilityResponse;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;
import static uk.gov.dhsc.htbhf.TestConstants.DWP_HOUSEHOLD_IDENTIFIER;
import static uk.gov.dhsc.htbhf.TestConstants.HMRC_HOUSEHOLD_IDENTIFIER;
import static uk.gov.dhsc.htbhf.TestConstants.MAGGIE_AND_LISA_DOBS;
import static uk.gov.dhsc.htbhf.TestConstants.NO_HOUSEHOLD_IDENTIFIER_PROVIDED;

@SuppressWarnings("PMD.TooManyMethods")
public class CombinedIdAndEligibilityResponseTestDataFactory {

    public static CombinedIdentityAndEligibilityResponse anIdMatchFailedResponse() {
        return defaultBuilderWithIdentityNotMatchedValues().build();
    }

    public static CombinedIdentityAndEligibilityResponse anIdMatchedEligibilityNotConfirmedResponse() {
        return CombinedIdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.MATCHED)
                .eligibilityStatus(EligibilityOutcome.NOT_CONFIRMED)
                .qualifyingBenefits(QualifyingBenefits.NOT_SET)
                .mobilePhoneMatch(VerificationOutcome.NOT_SET)
                .emailAddressMatch(VerificationOutcome.NOT_SET)
                .addressLine1Match(VerificationOutcome.NOT_SET)
                .postcodeMatch(VerificationOutcome.NOT_SET)
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SET)
                .dwpHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .hmrcHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .dobOfChildrenUnder4(emptyList())
                .deathVerificationFlag(DeathVerificationFlag.N_A)
                .build();
    }

    public static CombinedIdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedPostcodeNotMatchedResponse() {
        return CombinedIdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.MATCHED)
                .eligibilityStatus(EligibilityOutcome.CONFIRMED)
                .qualifyingBenefits(QualifyingBenefits.NOT_SET)
                .mobilePhoneMatch(VerificationOutcome.NOT_SET)
                .emailAddressMatch(VerificationOutcome.NOT_SET)
                .addressLine1Match(VerificationOutcome.MATCHED)
                .postcodeMatch(VerificationOutcome.NOT_MATCHED)
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SET)
                .dwpHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .hmrcHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .dobOfChildrenUnder4(emptyList())
                .deathVerificationFlag(DeathVerificationFlag.N_A)
                .build();
    }

    public static CombinedIdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedAddressNotMatchedResponse() {
        return CombinedIdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.MATCHED)
                .eligibilityStatus(EligibilityOutcome.CONFIRMED)
                .qualifyingBenefits(QualifyingBenefits.NOT_SET)
                .mobilePhoneMatch(VerificationOutcome.NOT_SET)
                .emailAddressMatch(VerificationOutcome.NOT_SET)
                .addressLine1Match(VerificationOutcome.NOT_MATCHED)
                .postcodeMatch(VerificationOutcome.MATCHED)
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SET)
                .dwpHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .hmrcHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .dobOfChildrenUnder4(emptyList())
                .deathVerificationFlag(DeathVerificationFlag.N_A)
                .build();
    }

    public static CombinedIdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedUCResponseWithMatches(VerificationOutcome mobileVerification,
                                                                                                              VerificationOutcome emailVerification,
                                                                                                              List<LocalDate> childrenDobs) {
        return CombinedIdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.MATCHED)
                .eligibilityStatus(EligibilityOutcome.CONFIRMED)
                .qualifyingBenefits(QualifyingBenefits.UNIVERSAL_CREDIT)
                .mobilePhoneMatch(mobileVerification)
                .emailAddressMatch(emailVerification)
                .addressLine1Match(VerificationOutcome.MATCHED)
                .postcodeMatch(VerificationOutcome.MATCHED)
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SET)
                .dwpHouseholdIdentifier(DWP_HOUSEHOLD_IDENTIFIER)
                .hmrcHouseholdIdentifier(HMRC_HOUSEHOLD_IDENTIFIER)
                .deathVerificationFlag(DeathVerificationFlag.N_A)
                .dobOfChildrenUnder4(childrenDobs)
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

    private static CombinedIdentityAndEligibilityResponse.CombinedIdentityAndEligibilityResponseBuilder defaultBuilderWithIdentityNotMatchedValues() {
        return CombinedIdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.NOT_MATCHED)
                .eligibilityStatus(EligibilityOutcome.NOT_SET)
                .qualifyingBenefits(QualifyingBenefits.NOT_SET)
                .mobilePhoneMatch(VerificationOutcome.NOT_SET)
                .emailAddressMatch(VerificationOutcome.NOT_SET)
                .addressLine1Match(VerificationOutcome.NOT_SET)
                .postcodeMatch(VerificationOutcome.NOT_SET)
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SET)
                .dwpHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .hmrcHouseholdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .dobOfChildrenUnder4(emptyList())
                .deathVerificationFlag(DeathVerificationFlag.N_A);
    }

    private static CombinedIdentityAndEligibilityResponse.CombinedIdentityAndEligibilityResponseBuilder
            defaultBuilderWithEligibilityConfirmedUCResponseWithAllMatches() {
        return CombinedIdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.MATCHED)
                .eligibilityStatus(EligibilityOutcome.CONFIRMED)
                .qualifyingBenefits(QualifyingBenefits.UNIVERSAL_CREDIT)
                .mobilePhoneMatch(VerificationOutcome.MATCHED)
                .emailAddressMatch(VerificationOutcome.MATCHED)
                .addressLine1Match(VerificationOutcome.MATCHED)
                .postcodeMatch(VerificationOutcome.MATCHED)
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SUPPLIED)
                .dwpHouseholdIdentifier(DWP_HOUSEHOLD_IDENTIFIER)
                .hmrcHouseholdIdentifier(HMRC_HOUSEHOLD_IDENTIFIER)
                .deathVerificationFlag(DeathVerificationFlag.N_A)
                .dobOfChildrenUnder4(MAGGIE_AND_LISA_DOBS);
    }

}

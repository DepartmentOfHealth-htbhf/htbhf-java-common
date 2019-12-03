package uk.gov.dhsc.htbhf.eligibility.model.testhelper;

import uk.gov.dhsc.htbhf.dwp.model.v2.*;
import uk.gov.dhsc.htbhf.eligibility.model.CombinedIdentityAndEligibilityResponse;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;
import static uk.gov.dhsc.htbhf.TestConstants.DWP_HOUSEHOLD_IDENTIFIER;
import static uk.gov.dhsc.htbhf.TestConstants.HMRC_HOUSEHOLD_IDENTIFIER;
import static uk.gov.dhsc.htbhf.TestConstants.MAGGIE_AND_LISA_DOBS;
import static uk.gov.dhsc.htbhf.TestConstants.NO_HOUSEHOLD_IDENTIFIER_PROVIDED;

public class CombinedIdentityAndEligibilityResponseTestDataFactory {

    public static CombinedIdentityAndEligibilityResponse anIdentityMatchFailedResponse() {
        return defaultBuilderWithIdentityNotMatchedValues().build();
    }

    public static CombinedIdentityAndEligibilityResponse anIdentityMatchedEligibilityNotConfirmedResponse() {
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

    public static CombinedIdentityAndEligibilityResponse anIdentityMatchedEligibilityConfirmedPostcodeNotMatchedResponse() {
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

    public static CombinedIdentityAndEligibilityResponse anIdentityMatchedEligibilityConfirmedAddressNotMatchedResponse() {
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

    public static CombinedIdentityAndEligibilityResponse anIdentityMatchedEligibilityConfirmedUCResponseWithMatches(VerificationOutcome mobileVerification,
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

    public static CombinedIdentityAndEligibilityResponse anIdentityMatchedEligibilityConfirmedUCResponseWithAllMatches() {
        return anIdentityMatchedEligibilityConfirmedUCResponseWithAllMatches(MAGGIE_AND_LISA_DOBS);
    }

    public static CombinedIdentityAndEligibilityResponse anIdentityMatchedEligibilityConfirmedUCResponseWithAllMatches(List<LocalDate> childrenDobs) {
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
                .dobOfChildrenUnder4(childrenDobs)
                .build();
    }

    public static CombinedIdentityAndEligibilityResponse.CombinedIdentityAndEligibilityResponseBuilder defaultBuilderWithIdentityNotMatchedValues() {
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

}

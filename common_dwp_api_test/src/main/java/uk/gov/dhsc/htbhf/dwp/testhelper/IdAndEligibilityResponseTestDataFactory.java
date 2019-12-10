package uk.gov.dhsc.htbhf.dwp.testhelper;

import uk.gov.dhsc.htbhf.dwp.model.*;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;
import static uk.gov.dhsc.htbhf.TestConstants.DWP_HOUSEHOLD_IDENTIFIER;
import static uk.gov.dhsc.htbhf.TestConstants.MAGGIE_AND_LISA_DOBS;
import static uk.gov.dhsc.htbhf.TestConstants.NO_HOUSEHOLD_IDENTIFIER_PROVIDED;

@SuppressWarnings("PMD.TooManyMethods")
public class IdAndEligibilityResponseTestDataFactory {

    public static IdentityAndEligibilityResponse anIdMatchFailedResponse() {
        return defaultBuilderWithIdentityNotMatchedValues().build();
    }

    public static IdentityAndEligibilityResponse anIdMatchedEligibilityNotConfirmedResponse() {
        return IdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.MATCHED)
                .eligibilityStatus(EligibilityOutcome.NOT_CONFIRMED)
                .qualifyingBenefits(QualifyingBenefits.NOT_SET)
                .mobilePhoneMatch(VerificationOutcome.NOT_SET)
                .emailAddressMatch(VerificationOutcome.NOT_SET)
                .addressLine1Match(VerificationOutcome.NOT_SET)
                .postcodeMatch(VerificationOutcome.NOT_SET)
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SET)
                .householdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .dobOfChildrenUnder4(emptyList())
                .deathVerificationFlag(DeathVerificationFlag.N_A)
                .build();
    }

    public static IdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedPostcodeNotMatchedResponse() {
        return IdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.MATCHED)
                .eligibilityStatus(EligibilityOutcome.CONFIRMED)
                .qualifyingBenefits(QualifyingBenefits.NOT_SET)
                .mobilePhoneMatch(VerificationOutcome.NOT_SET)
                .emailAddressMatch(VerificationOutcome.NOT_SET)
                .addressLine1Match(VerificationOutcome.MATCHED)
                .postcodeMatch(VerificationOutcome.NOT_MATCHED)
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SET)
                .householdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .dobOfChildrenUnder4(emptyList())
                .deathVerificationFlag(DeathVerificationFlag.N_A)
                .build();
    }

    public static IdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedAddressNotMatchedResponse() {
        return IdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.MATCHED)
                .eligibilityStatus(EligibilityOutcome.CONFIRMED)
                .qualifyingBenefits(QualifyingBenefits.NOT_SET)
                .mobilePhoneMatch(VerificationOutcome.NOT_SET)
                .emailAddressMatch(VerificationOutcome.NOT_SET)
                .addressLine1Match(VerificationOutcome.NOT_MATCHED)
                .postcodeMatch(VerificationOutcome.MATCHED)
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SET)
                .householdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .dobOfChildrenUnder4(emptyList())
                .deathVerificationFlag(DeathVerificationFlag.N_A)
                .build();
    }

    public static IdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedUCResponseWithMatches(VerificationOutcome mobileVerification,
                                                                                                      VerificationOutcome emailVerification,
                                                                                                      List<LocalDate> childrenDobs) {
        return IdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.MATCHED)
                .eligibilityStatus(EligibilityOutcome.CONFIRMED)
                .qualifyingBenefits(QualifyingBenefits.UNIVERSAL_CREDIT)
                .mobilePhoneMatch(mobileVerification)
                .emailAddressMatch(emailVerification)
                .addressLine1Match(VerificationOutcome.MATCHED)
                .postcodeMatch(VerificationOutcome.MATCHED)
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SET)
                .householdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .deathVerificationFlag(DeathVerificationFlag.N_A)
                .dobOfChildrenUnder4(childrenDobs)
                .build();
    }

    public static IdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedUCResponseWithAllMatches() {
        return anIdMatchedEligibilityConfirmedUCResponseWithAllMatches(VerificationOutcome.NOT_SUPPLIED, MAGGIE_AND_LISA_DOBS);
    }

    public static IdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedUCResponseWithAllMatches(List<LocalDate> childrenDobs) {
        return anIdMatchedEligibilityConfirmedUCResponseWithAllMatches(VerificationOutcome.NOT_SUPPLIED, childrenDobs);
    }

    public static IdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedUCResponseWithAllMatches(VerificationOutcome pregnantChildDOBMatch) {
        return anIdMatchedEligibilityConfirmedUCResponseWithAllMatches(pregnantChildDOBMatch, MAGGIE_AND_LISA_DOBS);
    }

    public static IdentityAndEligibilityResponse anIdMatchedEligibilityConfirmedUCResponseWithAllMatches(VerificationOutcome pregnantChildDOBMatch,
                                                                                                         List<LocalDate> childrenDobs) {
        return IdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.MATCHED)
                .eligibilityStatus(EligibilityOutcome.CONFIRMED)
                .qualifyingBenefits(QualifyingBenefits.UNIVERSAL_CREDIT)
                .mobilePhoneMatch(VerificationOutcome.MATCHED)
                .emailAddressMatch(VerificationOutcome.MATCHED)
                .addressLine1Match(VerificationOutcome.MATCHED)
                .postcodeMatch(VerificationOutcome.MATCHED)
                .pregnantChildDOBMatch(pregnantChildDOBMatch)
                .householdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .deathVerificationFlag(DeathVerificationFlag.N_A)
                .dobOfChildrenUnder4(childrenDobs)
                .build();
    }

    public static IdentityAndEligibilityResponse.IdentityAndEligibilityResponseBuilder defaultBuilderWithIdentityNotMatchedValues() {
        return IdentityAndEligibilityResponse.builder()
                .identityStatus(IdentityOutcome.NOT_MATCHED)
                .eligibilityStatus(EligibilityOutcome.NOT_SET)
                .qualifyingBenefits(QualifyingBenefits.NOT_SET)
                .mobilePhoneMatch(VerificationOutcome.NOT_SET)
                .emailAddressMatch(VerificationOutcome.NOT_SET)
                .addressLine1Match(VerificationOutcome.NOT_SET)
                .postcodeMatch(VerificationOutcome.NOT_SET)
                .pregnantChildDOBMatch(VerificationOutcome.NOT_SET)
                .householdIdentifier(NO_HOUSEHOLD_IDENTIFIER_PROVIDED)
                .dobOfChildrenUnder4(emptyList())
                .deathVerificationFlag(DeathVerificationFlag.N_A);
    }

    public static IdentityAndEligibilityResponse anAllMatchedEligibilityConfirmedUCResponseWithHouseholdId() {
        return addHouseholdIdentifier(anIdMatchedEligibilityConfirmedUCResponseWithAllMatches(), DWP_HOUSEHOLD_IDENTIFIER);
    }

    public static IdentityAndEligibilityResponse anAllMatchedEligibilityConfirmedUCResponseWithHouseholdId(List<LocalDate> dateOfBirthOfChildren,
                                                                                                           String householdIdentifier) {
        return addHouseholdIdentifier(anIdMatchedEligibilityConfirmedUCResponseWithAllMatches(dateOfBirthOfChildren),
                householdIdentifier);
    }

    public static IdentityAndEligibilityResponse addHouseholdIdentifier(IdentityAndEligibilityResponse originalResponse, String householdIdentifier) {
        return originalResponse.toBuilder()
                .householdIdentifier(householdIdentifier)
                .build();
    }

}

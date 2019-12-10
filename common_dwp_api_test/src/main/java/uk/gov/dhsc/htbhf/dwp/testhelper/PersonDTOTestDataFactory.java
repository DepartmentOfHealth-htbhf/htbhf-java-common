package uk.gov.dhsc.htbhf.dwp.testhelper;

import uk.gov.dhsc.htbhf.dwp.model.PersonDTO;

import java.time.LocalDate;

import static uk.gov.dhsc.htbhf.TestConstants.*;

@SuppressWarnings("PMD.TooManyMethods")
public class PersonDTOTestDataFactory {

    public static PersonDTO aValidPersonDTO() {
        return validPersonBuilder().build();
    }

    public static PersonDTO aPersonDTOWithSurname(String surname) {
        return validPersonBuilder().surname(surname).build();
    }

    public static PersonDTO aPersonDTOWithNino(String nino) {
        return validPersonBuilder().nino(nino).build();
    }

    public static PersonDTO aPersonDTOWithSurnameAndNino(String surname, String nino) {
        return validPersonBuilder().surname(surname).nino(nino).build();
    }

    public static PersonDTO aPersonDTOWithSurnameAndMobile(String surname, String mobile) {
        return validPersonBuilder().surname(surname).mobilePhoneNumber(mobile).build();
    }

    public static PersonDTO aPersonDTOWithSurnameAndEmail(String surname, String email) {
        return validPersonBuilder().surname(surname).emailAddress(email).build();
    }

    public static PersonDTO aPersonDTOWithPostcode(String postcode) {
        return validPersonBuilder().postcode(postcode).build();
    }

    public static PersonDTO aPersonDTOWithEmailAddress(String emailAddress) {
        return validPersonBuilder().emailAddress(emailAddress).build();
    }

    public static PersonDTO aPersonDTOWithMobilePhoneNumber(String mobilePhoneNumber) {
        return validPersonBuilder().mobilePhoneNumber(mobilePhoneNumber).build();
    }

    public static PersonDTO aPersonDTOWithDateOfBirth(LocalDate dateOfBirth) {
        return validPersonBuilder().dateOfBirth(dateOfBirth).build();
    }

    public static PersonDTO aPersonDTOWithPregnantDependantDob(LocalDate dateOfBirth) {
        return validPersonBuilder().pregnantDependentDob(dateOfBirth).build();
    }

    public static PersonDTO aPersonDTOWithAddressLine1(String addressLine1) {
        return validPersonBuilder().addressLine1(addressLine1).build();
    }

    public static PersonDTO aValidPersonDTOWithMandatoryFieldsOnly() {
        return PersonDTO.builder()
                .nino(HOMER_NINO)
                .surname(SIMPSON_SURNAME)
                .addressLine1(SIMPSONS_ADDRESS_LINE_1)
                .postcode(SIMPSONS_POSTCODE)
                .dateOfBirth(HOMER_DATE_OF_BIRTH)
                .build();
    }

    private static PersonDTO.PersonDTOBuilder validPersonBuilder() {
        return PersonDTO.builder()
                .nino(HOMER_NINO)
                .surname(SIMPSON_SURNAME)
                .addressLine1(SIMPSONS_ADDRESS_LINE_1)
                .postcode(SIMPSONS_POSTCODE)
                .emailAddress(HOMER_EMAIL)
                .mobilePhoneNumber(HOMER_MOBILE)
                .dateOfBirth(HOMER_DATE_OF_BIRTH)
                .pregnantDependentDob(MAGGIE_DATE_OF_BIRTH);
    }
}

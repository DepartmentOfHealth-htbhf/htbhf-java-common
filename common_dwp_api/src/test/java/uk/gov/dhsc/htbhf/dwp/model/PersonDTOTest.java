package uk.gov.dhsc.htbhf.dwp.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.dhsc.htbhf.assertions.AbstractValidationTest;

import java.time.LocalDate;
import java.util.Set;
import javax.validation.ConstraintViolation;

import static uk.gov.dhsc.htbhf.assertions.ConstraintViolationAssert.assertThat;
import static uk.gov.dhsc.htbhf.dwp.testhelper.PersonDTOTestDataFactory.*;

class PersonDTOTest extends AbstractValidationTest {

    @Test
    void shouldSuccessfullyValidatePersonWithMandatoryFieldsOnly() {
        PersonDTO personDTO = aValidPersonDTOWithMandatoryFieldsOnly();
        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(personDTO);
        assertThat(violations).hasNoViolations();
    }

    @Test
    void shouldSuccessfullyValidatePersonWithAllFields() {
        PersonDTO personDTO = aValidPersonDTO();
        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(personDTO);
        assertThat(violations).hasNoViolations();
    }

    @Test
    void shouldFailValidationWithMissingSurname() {
        PersonDTO personDTO = aPersonDTOWithSurname(null);
        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(personDTO);
        assertThat(violations).hasSingleConstraintViolation("must not be null", "surname");
    }

    @Disabled("nino is optional for private beta and unique key is yet to be decided")
    @Test
    void shouldFailValidationWithMissingNino() {
        PersonDTO personDTO = aPersonDTOWithNino(null);
        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(personDTO);
        assertThat(violations).hasSingleConstraintViolation("must not be null", "nino");
    }

    @Test
    void shouldValidationWithMissingNino() {
        PersonDTO personDTO = aPersonDTOWithNino(null);
        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(personDTO);
        assertThat(violations).hasNoViolations();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "YYHU456781",
            "888888888",
            "ABCDEFGHI",
            "ZQQ123456CZ",
            "QQ123456T",
            "ZZ999999D"
    })
    void shouldFailValidationWithInvalidNino(String nino) {
        PersonDTO personDTO = aPersonDTOWithNino(nino);
        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(personDTO);
        assertThat(violations).hasSingleConstraintViolation(
                "must match \"^(?!BG|GB|NK|KN|TN|NT|ZZ)[A-CEGHJ-PR-TW-Z][A-CEGHJ-NPR-TW-Z](\\d{6})[A-D]$\"", "nino");
    }

    @Test
    void shouldFailValidationWithMissingDateOfBirth() {
        PersonDTO personDTO = aPersonDTOWithDateOfBirth(null);
        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(personDTO);
        assertThat(violations).hasSingleConstraintViolation("must not be null", "dateOfBirth");
    }

    @Test
    void shouldFailValidationWithDateOfBirthInFuture() {
        PersonDTO personDTO = aPersonDTOWithDateOfBirth(LocalDate.now().plusYears(1));
        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(personDTO);
        assertThat(violations).hasSingleConstraintViolation("must be a past date", "dateOfBirth");
    }

    @Test
    void shouldFailValidationWithMissingAddressLine1() {
        PersonDTO personDTO = aPersonDTOWithAddressLine1(null);
        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(personDTO);
        assertThat(violations).hasSingleConstraintViolation("must not be null", "addressLine1");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "AA1122BB",
            "A",
            "11AA21",
    })
    void shouldFailValidationWithInvalidPostcode(String postcode) {
        PersonDTO personDTO = aPersonDTOWithPostcode(postcode);
        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(personDTO);
        assertThat(violations).hasSingleConstraintViolation(
                "must match \"^(GIR ?0AA|[A-PR-UWYZ]([0-9]{1,2}|([A-HK-Y][0-9]([0-9ABEHMNPRV-Y])?)|[0-9][A-HJKPS-UW]) ?[0-9][ABD-HJLNP-UW-Z]{2})$\"",
                "postcode");
    }

    @Test
    void shouldFailValidationWithMissingPostcode() {
        PersonDTO personDTO = aPersonDTOWithPostcode(null);
        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(personDTO);
        assertThat(violations).hasSingleConstraintViolation("must not be null", "postcode");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "plainaddress",
            "#@%^%#$@#$@#.com",
            "@domain.com"
    })
    void shouldFailValidationWithInvalidEmailAddress(String emailAddress) {
        PersonDTO personDTO = aPersonDTOWithEmailAddress(emailAddress);
        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(personDTO);
        assertThat(violations).hasSingleConstraintViolation(
                "must match \"(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)\"",
                "emailAddress");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "plainaddress",
            "#@%^%#$@#$@#.com",
            "@domain.com"
    })
    void shouldFailValidationWithInvalidMobilePhoneNumber(String mobilePhoneNumber) {
        PersonDTO personDTO = aPersonDTOWithMobilePhoneNumber(mobilePhoneNumber);
        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(personDTO);
        assertThat(violations).hasSingleConstraintViolation(
                "must match \"^\\+44\\d{9,10}$\"",
                "mobilePhoneNumber");
    }

    @Test
    void shouldFailValidationWithPregnantDependantDobInFuture() {
        PersonDTO personDTO = aPersonDTOWithPregnantDependantDob(LocalDate.now().plusYears(1));
        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(personDTO);
        assertThat(violations).hasSingleConstraintViolation("must be a past date", "pregnantDependentDob");
    }

}

package uk.gov.dhsc.htbhf.dwp.model;

import org.junit.jupiter.api.Test;
import uk.gov.dhsc.htbhf.assertions.AbstractValidationTest;

import java.util.Set;
import javax.validation.ConstraintViolation;

import static uk.gov.dhsc.htbhf.assertions.ConstraintViolationAssert.assertThat;
import static uk.gov.dhsc.htbhf.dwp.testhelper.DWPEligibilityRequestTestDataFactory.aValidDWPEligibilityRequest;
import static uk.gov.dhsc.htbhf.dwp.testhelper.DWPEligibilityRequestTestDataFactory.aValidDWPEligibilityRequestWithEligibilityEndDate;
import static uk.gov.dhsc.htbhf.dwp.testhelper.DWPEligibilityRequestTestDataFactory.aValidDWPEligibilityRequestWithPerson;
import static uk.gov.dhsc.htbhf.dwp.testhelper.PersonDTOTestDataFactory.aPersonDTOWithSurname;

class DWPEligibilityRequestTest extends AbstractValidationTest {

    @Test
    void shouldSuccessfullyValidateRequest() {
        DWPEligibilityRequest request = aValidDWPEligibilityRequest();
        Set<ConstraintViolation<DWPEligibilityRequest>> violations = validator.validate(request);
        assertThat(violations).hasNoViolations();
    }

    @Test
    void shouldFailValidationWithNoPerson() {
        DWPEligibilityRequest request = aValidDWPEligibilityRequestWithPerson(null);
        Set<ConstraintViolation<DWPEligibilityRequest>> violations = validator.validate(request);
        assertThat(violations).hasSingleConstraintViolation("must not be null", "person");
    }

    @Test
    void shouldFailValidationWithInvalidPerson() {
        PersonDTO person = aPersonDTOWithSurname(null);
        DWPEligibilityRequest request = aValidDWPEligibilityRequestWithPerson(person);
        Set<ConstraintViolation<DWPEligibilityRequest>> violations = validator.validate(request);
        assertThat(violations).hasSingleConstraintViolation("must not be null", "person.surname");
    }

    @Test
    void shouldFailValidationWithInvalidEligibilityEndDate() {
        DWPEligibilityRequest request = aValidDWPEligibilityRequestWithEligibilityEndDate(null);
        Set<ConstraintViolation<DWPEligibilityRequest>> violations = validator.validate(request);
        assertThat(violations).hasSingleConstraintViolation("must not be null", "eligibilityEndDate");
    }

}

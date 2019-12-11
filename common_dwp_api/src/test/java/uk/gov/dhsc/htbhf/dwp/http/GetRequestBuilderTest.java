package uk.gov.dhsc.htbhf.dwp.http;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import uk.gov.dhsc.htbhf.dwp.model.DWPEligibilityRequest;
import uk.gov.dhsc.htbhf.dwp.model.PersonDTO;
import uk.gov.dhsc.htbhf.dwp.testhelper.PersonDTOTestDataFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.gov.dhsc.htbhf.TestConstants.*;
import static uk.gov.dhsc.htbhf.dwp.testhelper.DWPEligibilityRequestTestDataFactory.aValidDWPEligibilityRequest;
import static uk.gov.dhsc.htbhf.dwp.testhelper.DWPEligibilityRequestTestDataFactory.aValidDWPEligibilityRequestWithPerson;

class GetRequestBuilderTest {

    private GetRequestBuilder builder = new GetRequestBuilder();

    @Test
    void buildRequestWithHeaders() {
        //Given
        DWPEligibilityRequest request = aValidDWPEligibilityRequest();
        //When
        HttpEntity httpEntity = builder.buildRequestWithHeaders(request);
        //Then
        assertThat(httpEntity).isNotNull();
        assertThat(httpEntity.hasBody()).isFalse();
        HttpHeaders headers = httpEntity.getHeaders();
        assertCommonHeadersCorrect(headers);
        assertThat(headers.getFirst("emailAddress")).isEqualTo(HOMER_EMAIL);
        assertThat(headers.getFirst("pregnantDependentDob")).isEqualTo(MAGGIE_DATE_OF_BIRTH_STRING);
    }

    @Test
    void buildRequestWithMissingHeader() {
        //Given
        PersonDTO person = PersonDTOTestDataFactory.aPersonDTOWithEmailAddress(null);
        DWPEligibilityRequest request = aValidDWPEligibilityRequestWithPerson(person);
        //When
        HttpEntity httpEntity = builder.buildRequestWithHeaders(request);
        //Then
        assertThat(httpEntity).isNotNull();
        assertThat(httpEntity.hasBody()).isFalse();
        HttpHeaders headers = httpEntity.getHeaders();
        assertCommonHeadersCorrect(headers);
        assertThat(headers.containsKey("emailAddress")).isFalse();
        assertThat(headers.getFirst("pregnantDependentDob")).isEqualTo(MAGGIE_DATE_OF_BIRTH_STRING);
    }

    @Test
    void buildRequestWithMissingDateHeader() {
        //Given
        PersonDTO person = PersonDTOTestDataFactory.aPersonDTOWithPregnantDependantDob(null);
        DWPEligibilityRequest request = aValidDWPEligibilityRequestWithPerson(person);
        //When
        HttpEntity httpEntity = builder.buildRequestWithHeaders(request);
        //Then
        assertThat(httpEntity).isNotNull();
        assertThat(httpEntity.hasBody()).isFalse();
        HttpHeaders headers = httpEntity.getHeaders();
        assertCommonHeadersCorrect(headers);
        assertThat(headers.getFirst("emailAddress")).isEqualTo(HOMER_EMAIL);
        assertThat(headers.containsKey("pregnantDependentDob")).isFalse();
    }

    private void assertCommonHeadersCorrect(HttpHeaders headers) {
        LocalDate eligibilityEndDate = LocalDate.now().plusDays(28);
        String eligibilityEndDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(eligibilityEndDate);
        assertThat(headers.getFirst("surname")).isEqualTo(SIMPSON_SURNAME);
        assertThat(headers.getFirst("nino")).isEqualTo(HOMER_NINO);
        assertThat(headers.getFirst("dateOfBirth")).isEqualTo(HOMER_DATE_OF_BIRTH_STRING);
        assertThat(headers.getFirst("eligibilityEndDate")).isEqualTo(eligibilityEndDateString);
        assertThat(headers.getFirst("addressLine1")).isEqualTo(SIMPSONS_ADDRESS_LINE_1);
        assertThat(headers.getFirst("postcode")).isEqualTo(SIMPSONS_POSTCODE);
        assertThat(headers.getFirst("mobilePhoneNumber")).isEqualTo(HOMER_MOBILE);
        assertThat(headers.getFirst("ucMonthlyIncomeThreshold")).isEqualTo(String.valueOf(UC_MONTHLY_INCOME_THRESHOLD_IN_PENCE));
    }
}

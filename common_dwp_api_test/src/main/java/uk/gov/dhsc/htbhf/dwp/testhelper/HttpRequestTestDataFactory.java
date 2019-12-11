package uk.gov.dhsc.htbhf.dwp.testhelper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static uk.gov.dhsc.htbhf.TestConstants.*;

public class HttpRequestTestDataFactory {

    public static HttpEntity<Void> aValidEligibilityHttpEntity() {
        return buildHttpEntityWithNino(HOMER_NINO, SIMPSON_SURNAME);
    }

    public static HttpEntity<Void> anEligibilityHttpEntityWithNinoAndSurname(String nino, String surname) {
        return buildHttpEntityWithNino(nino, surname);
    }

    public static HttpEntity<Void> anInvalidEligibilityHttpEntity() {
        return buildHttpEntityWithNino("ZZZZZZZZZ", SIMPSON_SURNAME);
    }

    private static HttpEntity<Void> buildHttpEntityWithNino(String nino, String surname) {
        LocalDate eligibilityEndDate = LocalDate.now().plusDays(28);
        String eligibilityEndDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(eligibilityEndDate);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("surname", surname);
        httpHeaders.add("nino", nino);
        httpHeaders.add("dateOfBirth", HOMER_DATE_OF_BIRTH_STRING);
        httpHeaders.add("eligibilityEndDate", eligibilityEndDateString);
        httpHeaders.add("addressLine1", SIMPSONS_ADDRESS_LINE_1);
        httpHeaders.add("postcode", SIMPSONS_POSTCODE);
        httpHeaders.add("emailAddress", HOMER_EMAIL);
        httpHeaders.add("mobilePhoneNumber", HOMER_MOBILE);
        httpHeaders.add("pregnantDependentDob", MAGGIE_DATE_OF_BIRTH_STRING);
        httpHeaders.add("ucMonthlyIncomeThreshold", String.valueOf(UC_MONTHLY_INCOME_THRESHOLD_IN_PENCE));
        return new HttpEntity<>(httpHeaders);
    }
}

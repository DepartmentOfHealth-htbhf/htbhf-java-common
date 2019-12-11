package uk.gov.dhsc.htbhf.dwp.testhelper;

import uk.gov.dhsc.htbhf.dwp.model.DWPEligibilityRequest;
import uk.gov.dhsc.htbhf.dwp.model.PersonDTO;

import java.time.LocalDate;

import static uk.gov.dhsc.htbhf.TestConstants.UC_MONTHLY_INCOME_THRESHOLD_IN_PENCE;


public class DWPEligibilityRequestTestDataFactory {

    public static DWPEligibilityRequest aValidDWPEligibilityRequest() {
        return validDWPEligibilityRequestBuilder()
                .build();
    }

    public static DWPEligibilityRequest aValidDWPEligibilityRequestWithPerson(PersonDTO person) {
        return validDWPEligibilityRequestBuilder()
                .person(person)
                .build();
    }

    public static DWPEligibilityRequest aValidDWPEligibilityRequestWithEligibilityEndDate(LocalDate eligibilityEndDate) {
        return validDWPEligibilityRequestBuilder()
                .eligibilityEndDate(eligibilityEndDate)
                .build();
    }

    private static DWPEligibilityRequest.DWPEligibilityRequestBuilder validDWPEligibilityRequestBuilder() {
        return DWPEligibilityRequest.builder()
                .person(PersonDTOTestDataFactory.aValidPersonDTO())
                .eligibilityEndDate(LocalDate.now().plusDays(28))
                .ucMonthlyIncomeThresholdInPence(UC_MONTHLY_INCOME_THRESHOLD_IN_PENCE);
    }

}

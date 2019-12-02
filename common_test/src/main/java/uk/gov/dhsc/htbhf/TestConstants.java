package uk.gov.dhsc.htbhf;

import java.time.LocalDate;
import java.util.List;

public class TestConstants {

    public static final String DWP_HOUSEHOLD_IDENTIFIER = "dwpHousehold1";
    public static final String HMRC_HOUSEHOLD_IDENTIFIER = "hmrcHousehold1";
    public static final String NO_HOUSEHOLD_IDENTIFIER_PROVIDED = "";

    public static final LocalDate MAGGIE_DATE_OF_BIRTH = LocalDate.now().minusMonths(6);
    public static final LocalDate LISA_DATE_OF_BIRTH = LocalDate.now().minusMonths(24);
    public static final List<LocalDate> MAGGIE_AND_LISA_DOBS = List.of(MAGGIE_DATE_OF_BIRTH, LISA_DATE_OF_BIRTH);
}

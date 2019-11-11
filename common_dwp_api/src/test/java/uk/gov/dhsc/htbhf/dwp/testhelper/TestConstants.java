package uk.gov.dhsc.htbhf.dwp.testhelper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public final class TestConstants {

    //Names
    public static final String HOMER_FORENAME = "Homer";
    public static final String LISA_FORENAME = "Lisa";
    public static final String MARGE_FORENAME = "Marge";
    public static final String MAGGIE_FORENAME = "Maggie";
    public static final String BART_FORENAME = "BART";
    public static final String SIMPSON_SURNAME = "Simpson";
    //Contact details
    public static final String HOMER_EMAIL = "homer@simpson.com";
    public static final String MARGE_EMAIL = "marge@simpson.com";
    public static final String HOMER_MOBILE = "+447700900000";
    public static final String MARGE_MOBILE = "+447700900001";
    //NINOs
    public static final String MARGE_NINO = "EB123456D";
    public static final String HOMER_NINO_V1 = "EE123456C";
    public static final String HOMER_NINO_V2 = "MC123456C";
    public static final String MARGE_NINO_V2 = "MC123456A";
    //Address
    public static final String SIMPSONS_ADDRESS_LINE_1 = "742 Evergreen Terrace";
    public static final String SIMPSONS_ADDRESS_LINE_2 = "Mystery Spot";
    public static final String SIMPSONS_TOWN = "Springfield";
    public static final String SIMPSONS_POSTCODE = "AA1 1AA";
    //Dates of birth
    public static final String HOMER_DATE_OF_BIRTH_STRING = "1985-12-31";
    public static final String MARGE_DATE_OF_BIRTH_STRING = "1975-12-31";
    public static final LocalDate HOMER_DATE_OF_BIRTH = LocalDate.parse(HOMER_DATE_OF_BIRTH_STRING);
    public static final LocalDate MARGE_DATE_OF_BIRTH = LocalDate.parse(MARGE_DATE_OF_BIRTH_STRING);
    public static final LocalDate MAGGIE_DATE_OF_BIRTH = LocalDate.now().minusMonths(6);
    public static final String MAGGIE_DATE_OF_BIRTH_STRING = MAGGIE_DATE_OF_BIRTH.format(DateTimeFormatter.ISO_LOCAL_DATE);
    public static final LocalDate LISA_DOB = LocalDate.now().minusMonths(24);
    public static final LocalDate BART_DOB = LocalDate.now().minusMonths(48);
    public static final LocalDate SIX_MONTH_OLD = LocalDate.now().minusMonths(6).withDayOfMonth(1);
    public static final LocalDate THREE_YEAR_OLD = LocalDate.now().minusYears(3).withDayOfMonth(1);
    public static final LocalDate FIVE_YEAR_OLD = LocalDate.now().minusYears(5);
    public static final LocalDate TWENTY_YEAR_OLD = LocalDate.now().minusYears(20);
    public static final List<LocalDate> TWO_CHILDREN = asList(SIX_MONTH_OLD, THREE_YEAR_OLD);
    public static final List<LocalDate> SINGLE_THREE_YEAR_OLD = singletonList(THREE_YEAR_OLD);
    public static final List<LocalDate> SINGLE_SIX_MONTH_OLD = singletonList(SIX_MONTH_OLD);
    public static final List<LocalDate> MAGGIE_AND_LISA_DOBS = List.of(MAGGIE_DATE_OF_BIRTH, LISA_DOB);
    //Miscellaneous
    public static final LocalDate ELIGIBLE_END_DATE = LocalDate.parse("2019-03-01");
    public static final LocalDate ELIGIBLE_START_DATE = LocalDate.parse("2019-02-14");
    public static final BigDecimal UC_MONTHLY_INCOME_THRESHOLD = BigDecimal.valueOf(408);
    public static final int UC_MONTHLY_INCOME_THRESHOLD_IN_PENCE = 40800;
    public static final String SIMPSON_UC_HOUSEHOLD_IDENTIFIER = "ucHouseholdIdentifier";
    public static final String SIMPSON_LEGACY_HOUSEHOLD_IDENTIFIER = "legacyHouseholdIdentifier";
    public static final String NO_HOUSEHOLD_IDENTIFIER_PROVIDED = "";
}

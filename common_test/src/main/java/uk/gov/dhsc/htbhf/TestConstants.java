package uk.gov.dhsc.htbhf;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class TestConstants {

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
    public static final String SIMPSONS_COUNTY = "Borough";
    public static final String SIMPSONS_POSTCODE = "AA1 1AA";
    //Dates of birth
    public static final String HOMER_DATE_OF_BIRTH_STRING = "1985-12-31";
    public static final String MARGE_DATE_OF_BIRTH_STRING = "1975-12-31";
    public static final LocalDate HOMER_DATE_OF_BIRTH = LocalDate.parse(HOMER_DATE_OF_BIRTH_STRING);
    public static final LocalDate MARGE_DATE_OF_BIRTH = LocalDate.parse(MARGE_DATE_OF_BIRTH_STRING);
    public static final LocalDate MAGGIE_DATE_OF_BIRTH = LocalDate.now().minusMonths(6);
    public static final String MAGGIE_DATE_OF_BIRTH_STRING = MAGGIE_DATE_OF_BIRTH.format(DateTimeFormatter.ISO_LOCAL_DATE);
    public static final LocalDate LISA_DATE_OF_BIRTH = LocalDate.now().minusMonths(24);
    public static final String LISA_DATE_OF_BIRTH_STRING = LISA_DATE_OF_BIRTH.format(DateTimeFormatter.ISO_LOCAL_DATE);
    public static final LocalDate BART_DATE_OF_BIRTH = LocalDate.now().minusMonths(48);
    public static final String BART_DATE_OF_BIRTH_STRING = BART_DATE_OF_BIRTH.format(DateTimeFormatter.ISO_LOCAL_DATE);
    public static final LocalDate SIX_MONTH_OLD = LocalDate.now().minusMonths(6).withDayOfMonth(1);
    public static final LocalDate EXACTLY_SIX_MONTH_OLD = MAGGIE_DATE_OF_BIRTH;
    public static final LocalDate THREE_YEAR_OLD = LocalDate.now().minusYears(3).withDayOfMonth(1);
    public static final LocalDate EXACTLY_THREE_YEAR_OLD = LocalDate.now().minusYears(3);
    public static final LocalDate NEARLY_FOUR_YEAR_OLD = LocalDate.now().minusYears(4).plusWeeks(2);
    public static final LocalDate FIVE_YEAR_OLD = LocalDate.now().minusYears(5);
    public static final LocalDate TWENTY_YEAR_OLD = LocalDate.now().minusYears(20);
    //List of dates of birth
    public static final List<LocalDate> NO_CHILDREN = emptyList();
    public static final List<LocalDate> SINGLE_SIX_MONTH_OLD = singletonList(EXACTLY_SIX_MONTH_OLD);
    public static final List<LocalDate> TWO_CHILDREN_UNDER_ONE = List.of(EXACTLY_SIX_MONTH_OLD, EXACTLY_SIX_MONTH_OLD);
    public static final List<LocalDate> TWO_CHILDREN = asList(EXACTLY_SIX_MONTH_OLD, EXACTLY_THREE_YEAR_OLD);
    public static final List<LocalDate> TWO_CHILDREN_BETWEEN_ONE_AND_FOUR = List.of(LISA_DATE_OF_BIRTH, EXACTLY_THREE_YEAR_OLD);
    public static final List<LocalDate> SINGLE_THREE_YEAR_OLD = singletonList(EXACTLY_THREE_YEAR_OLD);
    public static final List<LocalDate> ONE_CHILD_FOUR_YEARS_OLD = singletonList(BART_DATE_OF_BIRTH);
    public static final List<LocalDate> ONE_CHILD_UNDER_ONE_AND_ONE_CHILD_BETWEEN_ONE_AND_FOUR = List.of(EXACTLY_SIX_MONTH_OLD, EXACTLY_THREE_YEAR_OLD);
    public static final List<LocalDate> MAGGIE_AND_LISA_DOBS = List.of(MAGGIE_DATE_OF_BIRTH, LISA_DATE_OF_BIRTH);
    public static final List<LocalDate> SINGLE_NEARLY_FOUR_YEAR_OLD = singletonList(NEARLY_FOUR_YEAR_OLD);
    public static final List<LocalDate> SINGLE_FIVE_YEAR_OLD = singletonList(FIVE_YEAR_OLD);
    //Miscellaneous
    public static final LocalDate ELIGIBLE_END_DATE = LocalDate.parse("2019-03-01");
    public static final LocalDate ELIGIBLE_START_DATE = LocalDate.parse("2019-02-14");
    public static final BigDecimal UC_MONTHLY_INCOME_THRESHOLD = BigDecimal.valueOf(408);
    public static final int UC_MONTHLY_INCOME_THRESHOLD_IN_PENCE = 40800;
    public static final String SIMPSON_UC_HOUSEHOLD_IDENTIFIER = "ucHouseholdIdentifier";
    public static final String SIMPSON_LEGACY_HOUSEHOLD_IDENTIFIER = "legacyHouseholdIdentifier";
    public static final String DWP_HOUSEHOLD_IDENTIFIER = "dwpHousehold1";
    public static final String HMRC_HOUSEHOLD_IDENTIFIER = "hmrcHousehold1";
    public static final String NO_HOUSEHOLD_IDENTIFIER_PROVIDED = "";
}

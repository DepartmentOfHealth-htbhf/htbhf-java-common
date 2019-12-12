package uk.gov.dhsc.htbhf.eligibility.model.testhelper;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChildDobGeneratorTest {


    @Test
    void shouldGenerateBirthdaysForSixMonthOldTwins() {

        List<LocalDate> children = ChildDobGenerator.createDatesOfBirthForChildren("AA220000A");

        LocalDate sixMonthOld = LocalDate.now().minusMonths(6).withDayOfMonth(1);
        assertThat(children).contains(sixMonthOld, sixMonthOld);
    }

    @Test
    void shouldGenerateBirthdaysForSixMonthOldAndThreeYearOld() {

        List<LocalDate> children = ChildDobGenerator.createDatesOfBirthForChildren("AA120000A");

        LocalDate sixMonthOld = LocalDate.now().minusMonths(6).withDayOfMonth(1);
        LocalDate threeYearOld = LocalDate.now().minusYears(3).withDayOfMonth(1);
        assertThat(children).contains(sixMonthOld, threeYearOld);
    }

    @Test
    void shouldGenerateBirthdayForThreeYearOld() {

        List<LocalDate> children = ChildDobGenerator.createDatesOfBirthForChildren("AA010000A");

        LocalDate threeYearOld = LocalDate.now().minusYears(3).withDayOfMonth(1);
        assertThat(children).contains(threeYearOld);
    }

    @Test
    void shouldGenerateEmptyList() {

        List<LocalDate> children = ChildDobGenerator.createDatesOfBirthForChildren("AA000000A");

        assertThat(children).isEmpty();
    }

}
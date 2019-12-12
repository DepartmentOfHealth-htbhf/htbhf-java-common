package uk.gov.dhsc.htbhf.eligibility.model.testhelper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.nCopies;

public class ChildDobGenerator {

    public static final int CHILDREN_UNDER_ONE_NINO_POSITION = 2;
    public static final int CHILDREN_UNDER_FOUR_NINO_POSITION = 3;

    /**
     * Creates a list of dates of birth. All dates are either the first of the month six months ago (for under 1's),
     * or the first of the month three years ago (for under 4's). So if there are three birthdays under one, they'd be triplets.
     * The number of each type (6 month old and 3 year old) of birthday is derived from the first two numeric digits of the NINO
     * ({@link #CHILDREN_UNDER_ONE_NINO_POSITION} and {@link #CHILDREN_UNDER_FOUR_NINO_POSITION}).
     * The number of children under one is given in the first numeric digit and is subtracted from the total number of children under 4
     * (the second numeric digit) to determine the number of three-year-olds.
     *
     * @param nino the NINO used to derive the desired number and ages of children.
     * @return a list of dates of birth - either six months or three years old.
     */
    public static List<LocalDate> createDatesOfBirthForChildren(String nino) {
        Integer childrenUnderFour = getNumberOfChildrenUnderFour(nino);
        Integer childrenUnderOne = getNumberOfChildrenUnderOne(childrenUnderFour, nino);
        return createDatesOfBirthForChildren(childrenUnderOne, childrenUnderFour);
    }

    /**
     * Creates a list of dates of birth. All dates are either the first of the month six months ago (for under 1's),
     * or the first of the month three years ago (for under 4's).
     *
     * @param numberOfChildrenUnderOne  the number of six-month old dates to include in the response
     * @param numberOfChildrenUnderFour the total number of children. The number of three year olds is derived by subtracting the number of children under
     *                                  one from this.
     * @return a list of dates of birth - either six months or three years old.
     */
    public static List<LocalDate> createDatesOfBirthForChildren(Integer numberOfChildrenUnderOne, Integer numberOfChildrenUnderFour) {
        List<LocalDate> childrenUnderOne = nCopies(numberOfChildrenUnderOne, getDateOfBirthOfUnderOneYearOld());
        List<LocalDate> childrenBetweenOneAndFour = nCopies(numberOfChildrenUnderFour - numberOfChildrenUnderOne, getDateOfBirthOfThreeYearOld());
        return Stream.concat(childrenUnderOne.stream(), childrenBetweenOneAndFour.stream()).collect(Collectors.toList());
    }

    // We always make sure that there is no ambiguity over the date by setting it to the
    // first day of the month 6 months ago.
    public static LocalDate getDateOfBirthOfUnderOneYearOld() {
        return LocalDate.now().minusMonths(6).withDayOfMonth(1);
    }

    // We always make sure that there is no ambiguity over the date by setting it to the
    // first day of the month 3 years ago.
    public static LocalDate getDateOfBirthOfThreeYearOld() {
        return LocalDate.now().minusYears(3).withDayOfMonth(1);
    }

    public static Integer getNumberOfChildrenUnderOne(Integer childrenUnderFour, String nino) {
        Integer childrenUnderOne = Character.getNumericValue(nino.charAt(CHILDREN_UNDER_ONE_NINO_POSITION));
        return (childrenUnderOne > childrenUnderFour) ? childrenUnderFour : childrenUnderOne;
    }

    public static Integer getNumberOfChildrenUnderFour(String nino) {
        return Character.getNumericValue(nino.charAt(CHILDREN_UNDER_FOUR_NINO_POSITION));
    }

}

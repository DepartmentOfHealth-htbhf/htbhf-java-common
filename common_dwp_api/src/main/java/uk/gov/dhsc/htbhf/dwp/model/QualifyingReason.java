package uk.gov.dhsc.htbhf.dwp.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

public enum QualifyingReason {
    UNIVERSAL_CREDIT,
    EMPLOYMENT_AND_SUPPORT_ALLOWANCE,
    INCOME_SUPPORT,
    JOBSEEKERS_ALLOWANCE,
    PENSION_CREDIT,
    CHILD_TAX_CREDIT,
    UNDER_18,
    NOT_SET;

    @JsonValue
    public String getResponseValue() {
        return this.name().toLowerCase(Locale.getDefault());
    }
}
package uk.gov.dhsc.htbhf.eligibility.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

/**
 * The possible values for the eligibility of a claimant.
 */
@JsonFormat(shape = JsonFormat.Shape.STRING)
@ApiModel(description = "The eligibility status")
public enum EligibilityStatus {

    ELIGIBLE,
    INELIGIBLE,
    PENDING,
    NO_MATCH,
    ERROR,
    DUPLICATE
}

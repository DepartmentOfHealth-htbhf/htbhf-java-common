package uk.gov.dhsc.htbhf.assertions;

import org.springframework.http.ResponseEntity;
import uk.gov.dhsc.htbhf.errorhandler.ErrorResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Contains common assertions that are used by integration tests.
 */
public class IntegrationTestAssertions {

    /**
     * Asserts that the given response entity has a BAD_REQUEST status code and the specified validation error within
     * its list of field errors. Note - if this has more than one error then this will fail.
     * @param errorResponse The response to check
     * @param field The field which has the validation failure
     * @param errorMessage The error message we are expecting.
     */
    public static void assertValidationErrorInResponse(ResponseEntity<ErrorResponse> errorResponse, String field, String errorMessage) {
        assertThat(errorResponse.getStatusCode()).isEqualTo(BAD_REQUEST);
        assertThat(errorResponse.getBody()).isNotNull();
        assertThat(errorResponse.getBody().getFieldErrors()).hasSize(1);
        assertThat(errorResponse.getBody().getFieldErrors().get(0).getField()).isEqualTo(field);
        assertThat(errorResponse.getBody().getFieldErrors().get(0).getMessage()).isEqualTo(errorMessage);
        assertThat(errorResponse.getBody().getRequestId()).isNotNull();
        assertThat(errorResponse.getBody().getTimestamp()).isNotNull();
        assertThat(errorResponse.getBody().getMessage()).isEqualTo("There were validation issues with the request.");
    }
}

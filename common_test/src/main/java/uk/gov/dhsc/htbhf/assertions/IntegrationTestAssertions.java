package uk.gov.dhsc.htbhf.assertions;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.dhsc.htbhf.errorhandler.ErrorResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Contains common assertions that are used by integration tests.
 */
public class IntegrationTestAssertions {

    /**
     * Asserts that the given response entity has a BAD_REQUEST status code and the specified validation error within
     * its list of field errors. Note - if this has more than one error then this will fail.
     *
     * @param errorResponse     The response to check
     * @param field             The field which has the validation failure
     * @param fieldErrorMessage The error message we are expecting for the field.
     */
    public static void assertValidationErrorInResponse(ResponseEntity<ErrorResponse> errorResponse, String field, String fieldErrorMessage) {
        assertErrorInResponse(errorResponse, BAD_REQUEST, "There were validation issues with the request.");
        assertFieldErrorInResponse(errorResponse, field, fieldErrorMessage);
    }

    /**
     * Asserts that the error response is correct if we are expecting an Internal Server Error.
     *
     * @param errorResponse The response to check
     */
    public static void assertInternalServerErrorResponse(ResponseEntity<ErrorResponse> errorResponse) {
        assertErrorInResponse(errorResponse, INTERNAL_SERVER_ERROR, "An internal server error occurred");
    }

    /**
     * Asserts that the given response has a status code of BAD_REQUEST and that the specified field is given in the list
     * of errors when the request could not be parsed - this generally happens when an invalid date format is provided
     * in the request.
     *
     * @param errorResponse     The response to check
     * @param field             The field which has the validation failure
     * @param fieldErrorMessage The error message we are expecting for the field.
     */
    public static void assertRequestCouldNotBeParsedErrorResponse(ResponseEntity<ErrorResponse> errorResponse, String field, String fieldErrorMessage) {
        assertErrorInResponse(errorResponse, BAD_REQUEST,"The request could not be parsed.");
        assertFieldErrorInResponse(errorResponse, field, fieldErrorMessage);
    }

    //Have to suppress SpotBugs warnings because it doesn't understand that fail() will stop the possible NPE being thrown on line 61.
    @SuppressFBWarnings("NP_NULL_ON_SOME_PATH")
    private static void assertFieldErrorInResponse(ResponseEntity<ErrorResponse> errorResponse, String field, String fieldErrorMessage) {
        ErrorResponse responseBody = errorResponse.getBody();
        if (responseBody == null) {
            fail("ErrorResponse body is null, no response body has been returned so cannot verify field error for field: " + field);
        }
        assertThat(responseBody.getFieldErrors()).hasSize(1);
        assertThat(responseBody.getFieldErrors().get(0).getField()).isEqualTo(field);
        assertThat(responseBody.getFieldErrors().get(0).getMessage()).isEqualTo(fieldErrorMessage);
    }

    private static void assertErrorInResponse(ResponseEntity<ErrorResponse> errorResponse, HttpStatus status, String mainErrorMessage) {
        assertThat(errorResponse.getStatusCode()).isEqualTo(status);
        ErrorResponse responseBody = errorResponse.getBody();
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getRequestId()).isNotNull();
        assertThat(responseBody.getTimestamp()).isNotNull();
        assertThat(responseBody.getMessage()).isEqualTo(mainErrorMessage);
        assertThat(responseBody.getStatus()).isEqualTo(status.value());
    }
}

package uk.gov.dhsc.htbhf.assertions;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.dhsc.htbhf.errorhandler.ErrorResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static uk.gov.dhsc.htbhf.errorhandler.ErrorMessage.INTERNAL_SERVER_ERROR_MESSAGE;
import static uk.gov.dhsc.htbhf.errorhandler.ErrorMessage.UNREADABLE_ERROR_MESSAGE;
import static uk.gov.dhsc.htbhf.errorhandler.ErrorMessage.VALIDATION_ERROR_MESSAGE;

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
        assertErrorInResponse(errorResponse, BAD_REQUEST, VALIDATION_ERROR_MESSAGE);
        assertFieldErrorInResponse(errorResponse, field, fieldErrorMessage);
    }

    /**
     * Asserts that the error response is correct if we are expecting an Internal Server Error.
     *
     * @param errorResponse The response to check
     */
    public static void assertInternalServerErrorResponse(ResponseEntity<ErrorResponse> errorResponse) {
        assertErrorInResponse(errorResponse, INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE);
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
        assertErrorInResponse(errorResponse, BAD_REQUEST, UNREADABLE_ERROR_MESSAGE);
        assertFieldErrorInResponse(errorResponse, field, fieldErrorMessage);
    }

    /**
     * Assert that the given status and error message is in the given error response.
     *
     * @param errorResponse    The error response to check
     * @param status           The status to check for
     * @param mainErrorMessage The error message to check for
     */
    public static void assertErrorInResponse(ResponseEntity<ErrorResponse> errorResponse, HttpStatus status, String mainErrorMessage) {
        assertThat(errorResponse.getStatusCode()).isEqualTo(status);
        ErrorResponse responseBody = errorResponse.getBody();
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getRequestId()).isNotNull();
        assertThat(responseBody.getTimestamp()).isNotNull();
        assertThat(responseBody.getMessage()).isEqualTo(mainErrorMessage);
        assertThat(responseBody.getStatus()).isEqualTo(status.value());
    }

    /**
     * Asserts that the field error matches the given field and message.
     *
     * @param fieldError   The error to check
     * @param field        The field in the FieldError
     * @param errorMessage The error message to check for
     */
    public static void assertFieldError(ErrorResponse.FieldError fieldError, String field, String errorMessage) {
        assertThat(fieldError.getField()).isEqualTo(field);
        assertThat(fieldError.getMessage()).isEqualTo(errorMessage);
    }

    //Have to suppress SpotBugs warnings because it doesn't understand that fail() will stop the possible NPE being thrown on line 61.
    @SuppressFBWarnings("NP_NULL_ON_SOME_PATH")
    private static void assertFieldErrorInResponse(ResponseEntity<ErrorResponse> errorResponse, String field, String fieldErrorMessage) {
        ErrorResponse responseBody = errorResponse.getBody();
        if (responseBody == null) {
            fail("ErrorResponse body is null, no response body has been returned so cannot verify field error for field: " + field);
        }
        assertThat(responseBody.getFieldErrors()).hasSize(1);
        assertFieldError(responseBody.getFieldErrors().get(0), field, fieldErrorMessage);
    }

}

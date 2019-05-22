package uk.gov.dhsc.htbhf.errorhandler;

/**
 * Simple wrapper for error messages used by ErrorHandler (so we don't have transitive dependency problems).
 */
public class ErrorMessage {

    public static final String VALIDATION_ERROR_MESSAGE = "There were validation issues with the request.";
    public static final String UNREADABLE_ERROR_MESSAGE = "The request could not be parsed.";
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "An internal server error occurred";
}

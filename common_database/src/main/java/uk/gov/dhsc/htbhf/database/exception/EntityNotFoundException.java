package uk.gov.dhsc.htbhf.database.exception;

/**
 * Spring automatically wraps exceptions of the existing EntityNotFoundException class (in the package javax.persistence) in a new exception.
 * Doing so causes issues with error handling. Therefore creating a custom EntityNotFoundException class which won't be wrapped by spring.
 */
public class EntityNotFoundException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    public EntityNotFoundException() {
        super();
    }

    /**
     * Constructs a new EntityNotFoundException exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

}

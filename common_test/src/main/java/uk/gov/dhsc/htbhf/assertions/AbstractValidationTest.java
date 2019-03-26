package uk.gov.dhsc.htbhf.assertions;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.junit.jupiter.api.BeforeEach;

import java.nio.CharBuffer;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * A base class that can be used by unit tests that are checking validation annotations.
 * The Spotbugs suppressed warning is because the validator field is not seen to be used anywhere, however this will
 * be used by any unit test that extends it.
 */
@SuppressFBWarnings("URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD")
public abstract class AbstractValidationTest {

    // Create a string 501 characters long
    public static final String LONG_STRING = CharBuffer.allocate(501).toString().replace('\0', 'A');

    protected Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
}

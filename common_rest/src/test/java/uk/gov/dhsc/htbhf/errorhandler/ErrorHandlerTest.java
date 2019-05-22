package uk.gov.dhsc.htbhf.errorhandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.WebRequest;
import uk.gov.dhsc.htbhf.requestcontext.RequestContext;
import uk.gov.dhsc.htbhf.requestcontext.RequestContextHolder;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static uk.gov.dhsc.htbhf.assertions.IntegrationTestAssertions.assertErrorInResponse;
import static uk.gov.dhsc.htbhf.assertions.IntegrationTestAssertions.assertFieldError;
import static uk.gov.dhsc.htbhf.assertions.IntegrationTestAssertions.assertRequestCouldNotBeParsedErrorResponse;
import static uk.gov.dhsc.htbhf.errorhandler.ErrorMessage.VALIDATION_ERROR_MESSAGE;

@ExtendWith(MockitoExtension.class)
class ErrorHandlerTest {

    private static final String REQUEST_ID = "myRequestId";
    private static final String ERROR_MESSAGE_1 = "error1";
    private static final String ERROR_MESSAGE_2 = "error2";

    @Mock
    BindingResult bindingResult;
    @Mock
    HttpHeaders httpHeaders;
    @Mock
    WebRequest webRequest;
    @Mock
    RequestContextHolder requestContextHolder;
    @Mock
    RequestContext requestContext;

    @InjectMocks
    ErrorHandler handler;

    @Test
    @SuppressWarnings("unchecked")
    void shouldExtractFieldErrorsFromBindException() {
        // Given
        FieldError fieldError1 = new FieldError("objectName", "field1", ERROR_MESSAGE_1);
        FieldError fieldError2 = new FieldError("objectName", "field2", ERROR_MESSAGE_2);
        given(bindingResult.getFieldErrors()).willReturn(asList(fieldError1, fieldError2));
        given(requestContextHolder.get()).willReturn(requestContext);
        given(requestContext.getRequestId()).willReturn(REQUEST_ID);

        // When
        ResponseEntity<Object> responseEntity = handler.handleBindException(new BindException(bindingResult), httpHeaders, BAD_REQUEST, webRequest);

        // Then
        assertErrorInResponse((ResponseEntity) responseEntity, BAD_REQUEST, VALIDATION_ERROR_MESSAGE);
        ErrorResponse errorResponse = (ErrorResponse) responseEntity.getBody();
        assertThat(errorResponse.getRequestId()).isEqualTo(REQUEST_ID);
        List<ErrorResponse.FieldError> fieldErrors = errorResponse.getFieldErrors();
        assertThat(fieldErrors.size()).isEqualTo(2);
        assertFieldError(fieldErrors.get(0), "field1", ERROR_MESSAGE_1);
        assertFieldError(fieldErrors.get(1), "field2", ERROR_MESSAGE_2);
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldExtractGlobalErrorsFromBindException() {
        // Given
        ObjectError globalError1 = new ObjectError("object1", ERROR_MESSAGE_1);
        ObjectError globalError2 = new ObjectError("object2", ERROR_MESSAGE_2);
        given(bindingResult.getGlobalErrors()).willReturn(asList(globalError1, globalError2));
        given(requestContextHolder.get()).willReturn(requestContext);
        given(requestContext.getRequestId()).willReturn(REQUEST_ID);

        // When
        ResponseEntity<Object> responseEntity = handler.handleBindException(new BindException(bindingResult), httpHeaders, BAD_REQUEST, webRequest);

        // Then
        assertErrorInResponse((ResponseEntity) responseEntity, BAD_REQUEST, VALIDATION_ERROR_MESSAGE);
        ErrorResponse errorResponse = (ErrorResponse) responseEntity.getBody();
        assertThat(errorResponse.getRequestId()).isEqualTo(REQUEST_ID);
        List<ErrorResponse.FieldError> fieldErrors = errorResponse.getFieldErrors();
        assertThat(fieldErrors.size()).isEqualTo(2);
        assertFieldError(fieldErrors.get(0), "object1", ERROR_MESSAGE_1);
        assertFieldError(fieldErrors.get(1), "object2", ERROR_MESSAGE_2);
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldExtractDetailFromHttpMessageNotReadableException() {
        // Given
        InvalidFormatException cause = mock(InvalidFormatException.class);
        given(cause.getPath()).willReturn(asList(new Reference(null, "myComponent"), new Reference(null, "myProperty")));
        doReturn(LocalDate.class).when(cause).getTargetType();
        given(cause.getValue()).willReturn("my invalid date");
        HttpMessageNotReadableException ex = new HttpMessageNotReadableException("myMessage",
                cause,
                new MockHttpInputMessage("This is an error".getBytes(Charset.defaultCharset())));
        given(requestContextHolder.get()).willReturn(requestContext);
        given(requestContext.getRequestId()).willReturn(REQUEST_ID);

        // When
        ResponseEntity<Object> responseEntity = handler.handleHttpMessageNotReadable(ex, httpHeaders, BAD_REQUEST, webRequest);

        // Then
        assertRequestCouldNotBeParsedErrorResponse((ResponseEntity) responseEntity,
                "myComponent.myProperty", "'my invalid date' could not be parsed as a LocalDate");
    }

    @Test
    void shouldHandleHttpMessageNotReadableWithADifferentCause() {
        // Given
        HttpMessageNotReadableException ex = new HttpMessageNotReadableException("myMessage",
                new RuntimeException(),
                new MockHttpInputMessage("This is an error".getBytes(Charset.defaultCharset())));
        given(requestContextHolder.get()).willReturn(requestContext);
        given(requestContext.getRequestId()).willReturn(REQUEST_ID);

        // When
        ResponseEntity<Object> responseEntity = handler.handleHttpMessageNotReadable(ex, httpHeaders, BAD_REQUEST, webRequest);

        // Then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getBody()).isInstanceOf(ErrorResponse.class);
        ErrorResponse errorResponse = (ErrorResponse) responseEntity.getBody();
        assertThat(errorResponse.getRequestId()).isEqualTo(REQUEST_ID);
        assertThat(errorResponse.getFieldErrors()).isNull();
    }

}

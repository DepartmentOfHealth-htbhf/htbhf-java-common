package uk.gov.dhsc.htbhf.errorhandler;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static uk.gov.dhsc.htbhf.errorhandler.ExceptionDetailGenerator.constructExceptionDetail;

class ExceptionDetailGeneratorTest {

    @Test
    void shouldConstructExceptionDetailWithNullMessage() {
        // given
        Exception e = new RuntimeException();

        // when
        String result = constructExceptionDetail(e);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    void shouldConstructExceptionDetailWithExceptionAsOwnCause() {
        // given
        Exception e = mock(Exception.class);
        given(e.getCause()).willReturn(e);
        given(e.getMessage()).willReturn("foo");

        // when
        String result = constructExceptionDetail(e);

        // then
        assertThat(result).isEqualTo("foo");
    }

    @Test
    void shouldConstructExceptionDetailWithFirstStackFrame() {
        // given
        Exception e = new RuntimeException("foo");

        // when
        String result = constructExceptionDetail(e);

        // then
        assertThat(result).isNotNull();
        assertThat(result).startsWith("foo");
        assertThat(result).containsSequence(e.getStackTrace()[0].toString());
    }

    @Test
    void shouldConstructExceptionDetailWithoutStackTrace() {
        // given
        Exception e = mock(Exception.class);
        given(e.getMessage()).willReturn("foo");
        given(e.getStackTrace()).willReturn(null);

        // when
        String result = constructExceptionDetail(e);

        // then
        assertThat(result).isEqualTo("foo");
    }

    @Test
    void shouldConstructExceptionDetailWithEmptyStackTrace() {
        // given
        Exception e = mock(Exception.class);
        given(e.getMessage()).willReturn("foo");
        given(e.getStackTrace()).willReturn(new StackTraceElement[0]);

        // when
        String result = constructExceptionDetail(e);

        // then
        assertThat(result).isEqualTo("foo");
    }

    @Test
    void shouldConstructExceptionDetailWithAllCauses() {
        // given
        Exception e1 = new RuntimeException("ex1");
        Exception e2 = new RuntimeException("ex2", e1);
        Exception e3 = new RuntimeException("ex3", e2);

        // when
        String result = constructExceptionDetail(e3);

        // then
        assertThat(result).isNotNull();
        assertThat(result).containsSequence(e1.getMessage());
        assertThat(result).containsSequence(e2.getMessage());
        assertThat(result).containsSequence(e3.getMessage());
        assertThat(result).containsSequence(e1.getStackTrace()[0].toString());
        assertThat(result).containsSequence(e2.getStackTrace()[0].toString());
        assertThat(result).containsSequence(e3.getStackTrace()[0].toString());
    }

}

package uk.gov.dhsc.htbhf.reference;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


class ReferenceGeneratorTest {

    @ParameterizedTest
    @MethodSource("referenceLengths")
    public void testGenerateReference(int size) {
        String reference = ReferenceGenerator.generateReference(size);
        assertThat(reference).isNotNull();
        assertThat(reference.length()).isEqualTo(size);
        assertThat(reference).inHexadecimal();
    }

    private static Stream<Integer> referenceLengths() {
        return Stream.of(
                2,
                5,
                10
        );
    }

    @Test
    public void testNegativeSizeReferenceGenerator() {
        Assertions.assertThrows(NegativeArraySizeException.class, () -> {
            ReferenceGenerator.generateReference(-5);
        });
    }

    @Test
    public void testZeroSizeReferenceGenerator() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ReferenceGenerator.generateReference(0);
        });
    }
}
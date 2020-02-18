package uk.gov.dhsc.htbhf.reference;

import lombok.NoArgsConstructor;

import java.security.SecureRandom;

@NoArgsConstructor
public class ReferenceGenerator {

    private static final char[]  HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F','G','H','I','J','K','L','M',
            'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    /**
     * It generates reference of 10 alphanumeric characters with secure random logic.
     * @param size reference size.
     * @return generated reference.
     */
    public static String generateReference(final int size) {
        final SecureRandom secureRandom = new SecureRandom();
        final byte[] bytes = new byte[size];

        secureRandom.nextBytes(bytes);

        return getFormattedText(bytes);
    }

    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(final byte[] bytes) {
        final StringBuilder buf = new StringBuilder(bytes.length * 2);

        for (byte b : bytes) {
            buf.append(HEX_DIGITS[b & 0x0f]);
        }

        return buf.toString();
    }

}

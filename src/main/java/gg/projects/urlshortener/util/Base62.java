package gg.projects.urlshortener.util;

import org.springframework.util.Assert;

/**
 * @author gohar.gasparyan
 */
public class Base62 {

    public static final String BASE_62_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private Base62() {
    }

    /**
     * Encodes a decimal value to a Base62 String.
     *
     * @param b10 the decimal value to encode, must be nonnegative.
     * @return the number encoded as a Base62 String.
     * @throws IllegalArgumentException if the given long is negative
     */
    public static String encodeBase10(long b10) {
        Assert.isTrue(b10 >= 0, "negative value");

        String base62 = "";
        while (b10 > 0) {
            base62 = BASE_62_CHARS.charAt((int) (b10 % 62)) + base62;
            b10 /= 62;
        }
        return base62;

    }

    /**
     * Decodes a Base62 String returning a long.
     *
     * @param b62 the Base62 String to decode.
     * @return the decoded number as a long.
     * @throws IllegalArgumentException if the given String contains characters not
     *                                  specified in the constructor.
     */
    public static long decodeBase62(String b62) {
        checkCharsAreBase62Valid(b62);

        long base10 = 0;
        b62 = new StringBuffer(b62).reverse().toString();
        long count = 1;

        for (char character : b62.toCharArray()) {
            base10 += BASE_62_CHARS.indexOf(character) * count;
            count *= 62;
        }

        return base10;
    }

    private static void checkCharsAreBase62Valid(String b62) {
        b62.chars()
                .mapToObj(character -> (char) character)
                .forEach(character -> {
                            if (!BASE_62_CHARS.contains(String.valueOf(character))) {
                                throw new IllegalStateException("Invalid character(s) in string:" + character);
                            }
                        }
                );
    }


}

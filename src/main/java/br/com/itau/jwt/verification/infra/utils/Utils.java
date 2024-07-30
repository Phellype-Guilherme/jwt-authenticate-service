package br.com.itau.jwt.verification.infra.utils;

import java.util.stream.LongStream;

/**
 * Utility class for various helper methods.
 *
 * <p>This class provides utility methods for use throughout the application.</p>
 *
 *
 * @author Phellype Guilherme
 */
public final class Utils {

    /**
     * Private constructor to prevent instantiation.
     *
     * <p>Throws {@link UnsupportedOperationException} to indicate that this class cannot be instantiated.</p>
     */
    private Utils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Checks if a given number is prime.
     *
     * <p>This method uses an optimized algorithm to determine if the given number is a prime number.
     * It first handles small numbers and checks for divisibility by 2 and 3.
     * For larger numbers, it uses a stream to test divisibility by potential factors up to the square root of the number.</p>
     *
     * @param number the number to be checked
     * @return true if the number is prime, false otherwise
     */
    public static boolean numberIsPrime(int number) {

        if (number <= 1) return false;
        if (number <= 3) return true;
        if (number % 2 == 0 || number % 3 == 0) return false;

        return LongStream.rangeClosed(5, (long) Math.sqrt(number))
                .filter(i -> i % 6 == 1 || i % 6 == 5)
                .noneMatch(i -> number % i == 0);
    }
}

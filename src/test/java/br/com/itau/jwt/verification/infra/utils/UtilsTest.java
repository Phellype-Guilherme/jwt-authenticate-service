package br.com.itau.jwt.verification.infra.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Utils} class.
 *
 * @Author: Phellype Guilherme
 * @Date: 26/07/2024
 */

@ExtendWith(MockitoExtension.class)
class UtilsTest {

    /**
     * Test for {@link Utils#numberIsPrime(int)} with prime numbers.
     * This test checks if the method correctly identifies prime numbers.
     */
    @Test
    void testNumberIsPrimeWithPrimeNumbers() {
        assertTrue(Utils.numberIsPrime(2));
        assertTrue(Utils.numberIsPrime(3));
        assertTrue(Utils.numberIsPrime(5));
        assertTrue(Utils.numberIsPrime(7));
        assertTrue(Utils.numberIsPrime(11));
        assertTrue(Utils.numberIsPrime(13));
        assertTrue(Utils.numberIsPrime(17));
        assertTrue(Utils.numberIsPrime(19));
        assertTrue(Utils.numberIsPrime(23));
        assertTrue(Utils.numberIsPrime(29));
    }

    /**
     * Test for {@link Utils#numberIsPrime(int)} with non-prime numbers.
     * This test checks if the method correctly identifies non-prime numbers.
     */
    @Test
    void testNumberIsPrimeWithNonPrimeNumbers() {
        assertFalse(Utils.numberIsPrime(0));
        assertFalse(Utils.numberIsPrime(1));
        assertFalse(Utils.numberIsPrime(4));
        assertFalse(Utils.numberIsPrime(6));
        assertFalse(Utils.numberIsPrime(8));
        assertFalse(Utils.numberIsPrime(9));
        assertFalse(Utils.numberIsPrime(10));
        assertFalse(Utils.numberIsPrime(12));
        assertFalse(Utils.numberIsPrime(14));
        assertFalse(Utils.numberIsPrime(15));
    }

    /**
     * Test for {@link Utils#numberIsPrime(int)} with negative numbers.
     * This test checks if the method correctly identifies that negative numbers are not prime.
     */
    @Test
    void testNumberIsPrimeWithNegativeNumbers() {
        assertFalse(Utils.numberIsPrime(-1));
        assertFalse(Utils.numberIsPrime(-2));
        assertFalse(Utils.numberIsPrime(-3));
        assertFalse(Utils.numberIsPrime(-5));
    }
}
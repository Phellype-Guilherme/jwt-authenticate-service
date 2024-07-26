package br.com.itau.jwt.verification.infra.utils;

import java.util.stream.LongStream;

public class Utils {

    public static boolean numberIsPrime(int number) {

        if (number <= 1) return false;
        if (number <= 3) return true;
        if (number % 2 == 0 || number % 3 == 0) return false;

        return LongStream.rangeClosed(5, (long) Math.sqrt(number))
                .filter(i -> i % 6 == 1 || i % 6 == 5)
                .noneMatch(i -> number % i == 0);
    }
}

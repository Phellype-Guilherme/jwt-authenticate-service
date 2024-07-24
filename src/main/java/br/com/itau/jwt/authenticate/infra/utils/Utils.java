package br.com.itau.jwt.authenticate.infra.utils;

import java.util.stream.LongStream;

public class Utils {

    public static boolean numberIsPrime(int number) {
        // Tratamento de casos simples
        if (number <= 1) return false;
        if (number <= 3) return true; // 2 e 3 são primos
        if (number % 2 == 0 || number % 3 == 0) return false; // Elimina múltiplos de 2 e 3

        // Verificação usando o método de divisão até a raiz quadrada
        // Utiliza o algoritmo de otimização que verifica divisores de forma mais eficiente
        return LongStream.rangeClosed(5, (long) Math.sqrt(number))
                .filter(i -> i % 6 == 1 || i % 6 == 5) // Verifica divisores da forma 6k ± 1
                .noneMatch(i -> number % i == 0); // Se algum divisor divide o número, não é primo
    }
}

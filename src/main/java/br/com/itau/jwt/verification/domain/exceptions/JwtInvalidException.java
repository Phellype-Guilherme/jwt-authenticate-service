package br.com.itau.jwt.verification.domain.exceptions;


public class JwtInvalidException extends RuntimeException {

    public JwtInvalidException(String message) {
        super(message);
    }
}

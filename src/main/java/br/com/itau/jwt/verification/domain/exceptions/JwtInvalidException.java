package br.com.itau.jwt.verification.domain.exceptions;


/**
 * Exception thrown when a JWT is invalid.
 *
 * <p>This exception is used to indicate that a JWT has failed validation checks and is therefore invalid.</p>
 *
 *
 * @author Phellype Guilherme
 */
public class JwtInvalidException extends RuntimeException {

    public JwtInvalidException(String message) {
        super(message);
    }
}

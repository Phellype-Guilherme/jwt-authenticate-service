package br.com.itau.jwt.verification.domain.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 * This class handles exceptions thrown by the application and returns appropriate HTTP responses.
 *
 *
 * @author Phellype Guilherme
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handles {@link JwtInvalidException} and returns a 401 Unauthorized response.
     *
     * @param e the thrown {@link JwtInvalidException}
     * @return a {@link ResponseEntity} containing a boolean false and HTTP status 401
     */
    @ExceptionHandler(JwtInvalidException.class)
    public ResponseEntity<Boolean> handleJwtInvalidException(JwtInvalidException e) {
        log.error("JWT Invalid Exception: {}", e.getMessage());
        return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }

}

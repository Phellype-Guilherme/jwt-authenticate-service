package br.com.itau.jwt.verification.domain.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for {@link GlobalExceptionHandler}.
 *
 * @Author: Phellype Guilherme
 * @Date: 26/07/2024
 */
@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    /**
     * Test to verify the handling of {@link JwtInvalidException}.
     *
     * This test validates that when a {@link JwtInvalidException} is thrown,
     * the {@link GlobalExceptionHandler#handleJwtInvalidException(JwtInvalidException)} method
     * returns an HTTP 401 Unauthorized response with a false body.
     */
    @Test
    void testHandleJwtInvalidException() {
        JwtInvalidException exception = new JwtInvalidException("Invalid JWT");

        ResponseEntity<Boolean> response = globalExceptionHandler.handleJwtInvalidException(exception);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertFalse(response.getBody());
    }

}
package br.com.itau.jwt.verification.app.controller;

import br.com.itau.jwt.verification.domain.exceptions.JwtInvalidException;
import br.com.itau.jwt.verification.domain.services.JwtVerifyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link JwtVerificationController}.
 *
 * @Author: Phellype Guilherme
 * @Date: 26/07/2024
 */
@ExtendWith(MockitoExtension.class)
class JwtVerificationControllerTest {

    @Mock
    private JwtVerifyService jwtVerifyService;

    @InjectMocks
    private JwtVerificationController jwtVerificationController;

    /**
     * Test to verify valid JWT token.
     *
     * This test validates that a valid JWT token is correctly processed by the controller,
     * resulting in an HTTP 200 OK response with a true body.
     */
    @Test
    void testVerifyJwtValidToken() {
        String validJwt = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
        when(jwtVerifyService.validateJwt(validJwt)).thenReturn(true);

        ResponseEntity<Boolean> response = jwtVerificationController.verifyJwt(validJwt);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
        verify(jwtVerifyService, times(1)).validateJwt(validJwt);
    }

    /**
     * Test to verify invalid JWT token.
     *
     * This test validates that an invalid JWT token is correctly processed by the controller,
     * resulting in an HTTP 401 Unauthorized response with a false body.
     */
    @Test
    void testVerifyJwtInvalidToken() {
        String invalidJwt = "Bearer eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
        when(jwtVerifyService.validateJwt(invalidJwt)).thenReturn(false);

        ResponseEntity<Boolean> response = jwtVerificationController.verifyJwt(invalidJwt);

        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertFalse(response.getBody());
        verify(jwtVerifyService, times(1)).validateJwt(invalidJwt);
    }

    /**
     * Test to verify JWT token without Bearer prefix.
     *
     * This test validates that a JWT token without the Bearer prefix is correctly rejected by the controller,
     * resulting in a JwtInvalidException being thrown with the appropriate message.
     */
    @Test
    void testVerifyJwtMissingBearer() {
        String missingBearerJwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";

        JwtInvalidException exception = assertThrows(JwtInvalidException.class, () ->
                jwtVerificationController.verifyJwt(missingBearerJwt)
        );

        assertEquals("Invalid JWT without flag Bearer", exception.getMessage());
        verify(jwtVerifyService, never()).validateJwt(anyString());
    }
}
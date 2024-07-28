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
import static org.mockito.Mockito.when;

/**
 * Integration tests for the JwtVerificationController.
 *
 * @Author: Phellype Guilherme
 * @Date: 26/07/2024
 */

@ExtendWith(MockitoExtension.class)
class JwtVerificationControllerIntegrationTest {

    @Mock
    private JwtVerifyService jwtVerifyService;

    @InjectMocks
    private JwtVerificationController jwtVerificationController;

    /**
     * Test for verifying a valid JWT.
     *
     * @throws Exception if any error occurs during the test.
     */
    @Test
    void testVerifyJwtValid() {
        String authorizationHeader = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";

        when(jwtVerifyService.validateJwt(anyString())).thenReturn(true);

        ResponseEntity<Boolean> response = jwtVerificationController.verifyJwt(authorizationHeader);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
    }

    /**
     * Test for verifying an invalid JWT.
     *
     * @throws Exception if any error occurs during the test.
     */
    @Test
    void testVerifyJwtInvalid() {
        String authorizationHeader = "Bearer eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";

        when(jwtVerifyService.validateJwt(anyString())).thenReturn(false);

        ResponseEntity<Boolean> response = jwtVerificationController.verifyJwt(authorizationHeader);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertFalse(response.getBody());
    }

    /**
     * Test for verifying a JWT without the "Bearer" prefix.
     *
     * @throws JwtInvalidException if the JWT does not have the "Bearer" prefix.
     */
    @Test
    void testVerifyJwtNoBearerPrefix() {
        String authorizationHeader = "eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";

        assertThrows(JwtInvalidException.class, () -> jwtVerificationController.verifyJwt(authorizationHeader));
    }


}
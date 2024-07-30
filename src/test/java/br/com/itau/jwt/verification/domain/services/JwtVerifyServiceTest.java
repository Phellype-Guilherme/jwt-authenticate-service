package br.com.itau.jwt.verification.domain.services;

import br.com.itau.jwt.verification.domain.exceptions.JwtInvalidException;
import br.com.itau.jwt.verification.infra.components.validation.JwtValidatorComponent;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link JwtVerifyService}.
 *
 * @Author: Phellype Guilherme
 * @Date: 26/07/2024
 */
@ExtendWith(MockitoExtension.class)
class JwtVerifyServiceTest {

    @Mock
    private JwtValidatorComponent jwtValidatorComponent;

    @InjectMocks
    private JwtVerifyService jwtVerifyService;

    @Mock
    private DecodedJWT decodedJWT;

    /**
     * Test to validate a valid JWT token.
     */
    @Test
    void testValidateJwtValidToken() {
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";

        when(jwtValidatorComponent.validateJwtStructure(anyString())).thenReturn(true);
        when(jwtValidatorComponent.validateNumberOfClaims(anyMap())).thenReturn(true);
        when(jwtValidatorComponent.validateNameClaim(anyString())).thenReturn(true);
        when(jwtValidatorComponent.validateRoleClaim(anyString())).thenReturn(true);
        when(jwtValidatorComponent.validateSeedClaim(anyString())).thenReturn(true);

        boolean result = jwtVerifyService.validateJwt(token);

        assertTrue(result);
        verify(jwtValidatorComponent, times(1)).validateJwtStructure(anyString());
    }

    /**
     * Test to validate an invalid JWT token.
     */
    @Test
    void testValidateJwtInvalidToken() {
        String token = "Bearer eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";

        when(jwtValidatorComponent.validateJwtStructure(anyString())).thenReturn(false);

        boolean result = jwtVerifyService.validateJwt(token);

        assertFalse(result);
        verify(jwtValidatorComponent, times(1)).validateJwtStructure(anyString());
    }

    /**
     * Test to handle exception during JWT validation.
     */
    @Test
    void testValidateJwtException() {
        String token = "Bearer eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";

        when(jwtValidatorComponent.validateJwtStructure(anyString())).thenThrow(new JwtInvalidException("Invalid JWT"));

        assertThrows(JwtInvalidException.class, () -> jwtVerifyService.validateJwt(token));
        verify(jwtValidatorComponent, times(1)).validateJwtStructure(anyString());
    }
}
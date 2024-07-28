package br.com.itau.jwt.verification.infra.components.validation.impl;

import br.com.itau.jwt.verification.domain.exceptions.JwtInvalidException;
import com.auth0.jwt.interfaces.Claim;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Base64;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for {@link JwtValidatorComponentImpl}.
 *
 * @Author: Phellype Guilherme
 * @Date: 26/07/2024
 */

@ExtendWith(MockitoExtension.class)
class JwtValidatorComponentImplTest {

    private final JwtValidatorComponentImpl jwtValidatorComponent = new JwtValidatorComponentImpl();

    /**
     * Test to validate a valid JWT structure.
     */
    @Test
    void testValidateJwtStructureValid() {
        Base64.Encoder encoder = Base64.getUrlEncoder();
        String header = encoder.encodeToString("{\"alg\":\"HS256\",\"typ\":\"JWT\"}".getBytes());
        String payload = encoder.encodeToString("{\"Role\":\"Admin\",\"name\":\"John Doe\",\"Seed\":7841}".getBytes());
        String token = header + "." + payload + ".signature";

        assertTrue(jwtValidatorComponent.validateJwtStructure(token));
    }

    /**
     * Test to validate an invalid JWT structure.
     */
    @Test
    void testValidateJwtStructureInvalid() {
        String jwt = "invalid.jwt.token";

        assertThrows(JwtInvalidException.class, () -> jwtValidatorComponent.validateJwtStructure(jwt));
    }

    /**
     * Test to validate a JWT structure with more than 3 claims.
     */
    @Test
    void testValidateJwtStructureInvalidMoreThan3claims() {
        String jwt = "invalid.jwt.token.token";

        assertFalse(jwtValidatorComponent.validateJwtStructure(jwt));
    }

    /**
     * Test to validate a valid name claim.
     */
    @Test
    void testValidateNameClaimValid() {
        assertTrue(jwtValidatorComponent.validateNameClaim("John Doe"));
    }

    /**
     * Test to validate an invalid name claim.
     */
    @Test
    void testValidateNameClaimInvalid() {
        assertFalse(jwtValidatorComponent.validateNameClaim("Invalid@Name"));
    }

    /**
     * Test to validate a valid role claim.
     */
    @Test
    void testValidateRoleClaimValid() {
        assertTrue(jwtValidatorComponent.validateRoleClaim("Admin"));
    }


    /**
     * Test to validate an invalid role claim.
     */
    @Test
    void testValidateRoleClaimInvalid() {
        assertFalse(jwtValidatorComponent.validateRoleClaim("UnknownRole"));
    }

    /**
     * Test to validate a valid seed claim.
     */
    @Test
    void testValidateSeedClaimValid() {
        assertTrue(jwtValidatorComponent.validateSeedClaim("7"));
    }

    /**
     * Test to validate an invalid seed claim.
     */
    @Test
    void testValidateSeedClaimInvalid() {
        assertThrows(JwtInvalidException.class, () -> jwtValidatorComponent.validateSeedClaim("invalid"));
    }

    /**
     * Test to validate a valid number of claims.
     */
    @Test
    void testValidateNumberOfClaimsValid() {
        Map<String, Claim> claims = Map.of(
                "Name", mock(Claim.class),
                "Role", mock(Claim.class),
                "Seed", mock(Claim.class)
        );

        assertTrue(jwtValidatorComponent.validateNumberOfClaims(claims));
    }

    /**
     * Test to validate an invalid number of claims.
     */
    @Test
    void testValidateNumberOfClaimsInvalid() {
        Map<String, Claim> claims = Map.of(
                "Name", mock(Claim.class),
                "Role", mock(Claim.class)
        );

        assertFalse(jwtValidatorComponent.validateNumberOfClaims(claims));
    }
}

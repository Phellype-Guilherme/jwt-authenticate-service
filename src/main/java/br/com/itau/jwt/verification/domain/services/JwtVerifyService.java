package br.com.itau.jwt.verification.domain.services;

import br.com.itau.jwt.verification.domain.exceptions.JwtInvalidException;
import br.com.itau.jwt.verification.infra.components.validation.JwtValidatorComponent;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service class for verifying JWT token.
 * This class provides the logic to validate JWT tokens according to specific rules.
 *
 * @author Phellype Guilherme
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class JwtVerifyService {

    private final JwtValidatorComponent jwtValidatorComponent;

    /**
     * Validates a JWT token.
     *
     * <p>This method performs the following checks:</p>
     * <ul>
     *   <li>Validates the JWT structure</li>
     *   <li>Checks the number of claims</li>
     *   <li>Validates the 'Name' claim</li>
     *   <li>Validates the 'Role' claim</li>
     *   <li>Validates the 'Seed' claim</li>
     * </ul>
     *
     * @param token the JWT token to be validated
     * @return true if the token is valid according to the specified rules, false otherwise
     * @throws JwtInvalidException if the JWT is invalid
     */
    public boolean validateJwt(String token) {

        try {

            String jwtSimple = token.substring(7);

            if (jwtValidatorComponent.validateJwtStructure(jwtSimple)) {

                DecodedJWT decodedJWT = JWT.decode(jwtSimple);

                return jwtValidatorComponent.validateNumberOfClaims(decodedJWT.getClaims()) &&
                        jwtValidatorComponent.validateNameClaim(decodedJWT.getClaim("Name").asString()) &&
                        jwtValidatorComponent.validateRoleClaim(decodedJWT.getClaim("Role").asString()) &&
                        jwtValidatorComponent.validateSeedClaim(decodedJWT.getClaim("Seed").asString());
            }

            return false;

        } catch (Exception e) {
            log.error("Invalid JWT: {}", e.getMessage());
            throw new JwtInvalidException("Invalid JWT");
        }
    }
}

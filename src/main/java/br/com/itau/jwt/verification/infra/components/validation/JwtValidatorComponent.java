package br.com.itau.jwt.verification.infra.components.validation;

import com.auth0.jwt.interfaces.Claim;
import java.util.Map;

/**
 * Interface for validating JWT tokens.
 * Provides methods for validating the structure and specific claims of a JWT.
 *
 *
 * @author Phellype Guilherme
 */
public interface JwtValidatorComponent {

    /**
     * Validates the structure of the JWT token.
     *
     * @param token the JWT token to be validated
     * @return true if the JWT structure is valid, false otherwise
     */
    boolean validateJwtStructure(String token);

    /**
     * Validates the 'Name' claim of the JWT token.
     *
     * @param value the 'Name' claim to be validated
     * @return true if the 'Name' claim is valid, false otherwise
     */
    boolean validateNameClaim(String value);

    /**
     * Validates the 'Role' claim of the JWT token.
     *
     * @param value the 'Role' claim to be validated
     * @return true if the 'Role' claim is valid, false otherwise
     */
    boolean validateRoleClaim(String value);

    /**
     * Validates the 'Seed' claim of the JWT token.
     *
     * @param claim the 'Seed' claim to be validated
     * @return true if the 'Seed' claim is a prime number, false otherwise
     */
    boolean validateSeedClaim(String claim);

    /**
     * Validates the number of claims in the JWT token.
     *
     * @param value the map of claims in the JWT token
     * @return true if the JWT token contains exactly 'Name', 'Role', and 'Seed' claims, false otherwise
     */
    boolean validateNumberOfClaims(Map<String, Claim> value);

}

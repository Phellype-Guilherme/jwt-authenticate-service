package br.com.itau.jwt.verification.infra.components.validation.impl;

import br.com.itau.jwt.verification.domain.exceptions.JwtInvalidException;
import br.com.itau.jwt.verification.infra.components.validation.JwtValidatorComponent;
import br.com.itau.jwt.verification.infra.utils.Utils;
import com.auth0.jwt.interfaces.Claim;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Implementation of {@link JwtValidatorComponent} for validating JWT tokens.
 * This component class provides methods to validate the structure and specific claims of a JWT.
 *
 * <p>Date: 2024-07-26</p>
 *
 * @author Phellype Guilherme
 */
@Component
@Slf4j
public class JwtValidatorComponentImpl implements JwtValidatorComponent {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]{1,256}$");
    private static final Set<String> VALID_ROLES = Set.of("Admin", "Member", "External");

    /**
     * Validates the structure of the JWT token.
     *
     * @param jwt the JWT token to be validated
     * @return true if the JWT structure is valid, false otherwise
     * @throws JwtInvalidException if the JWT structure is invalid
     */
    @Override
    public boolean validateJwtStructure(String jwt) {

        try {

            String[] parts = jwt.split("\\.");

            if (parts.length != 3) {
                return false;
            }

            Base64.Decoder urlDecoder = Base64.getUrlDecoder();

            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.readTree(new String(urlDecoder.decode(parts[0])));
            objectMapper.readTree(new String(urlDecoder.decode(parts[1])));

            return true;
        } catch (Exception e) {
            log.error("Error jwt strucuture invalid: {}", e.getMessage());
            throw new JwtInvalidException("Error jwt strucuture invalid");
        }
    }

    /**
     * Validates the 'Name' claim of the JWT token.
     *
     * @param claim the 'Name' claim to be validated
     * @return true if the 'Name' claim is valid, false otherwise
     */
    @Override
    public boolean validateNameClaim(String claim) {
        return claim != null && NAME_PATTERN.matcher(claim).matches();
    }

    /**
     * Validates the 'Role' claim of the JWT token.
     *
     * @param claim the 'Role' claim to be validated
     * @return true if the 'Role' claim is valid, false otherwise
     */
    @Override
    public boolean validateRoleClaim(String claim) {
        return VALID_ROLES.contains(claim);
    }

    /**
     * Validates the 'Seed' claim of the JWT token.
     *
     * @param claim the 'Seed' claim to be validated
     * @return true if the 'Seed' claim is a prime number, false otherwise
     * @throws JwtInvalidException if the 'Seed' claim is not a valid number
     */
    @Override
    public boolean validateSeedClaim(String claim) {

        try {
            return claim != null && Utils.numberIsPrime(Integer.parseInt(claim));
        } catch (NumberFormatException e) {
            log.error("invalid number format: {}", e.getMessage());
            throw new JwtInvalidException("invalid number format");
        }
    }

    /**
     * Validates the number of claims in the JWT token.
     *
     * @param claimMap the map of claims in the JWT token
     * @return true if the JWT token contains exactly 'Name', 'Role', and 'Seed' claims, false otherwise
     */
    @Override
    public boolean validateNumberOfClaims(Map<String, Claim> claimMap) {

        return claimMap.keySet().equals(Set.of("Name", "Role", "Seed"));

    }
}


package br.com.itau.jwt.verification.infra.components.validation;

import com.auth0.jwt.interfaces.Claim;
import java.util.Map;

public interface JwtValidatorComponent {

    boolean validateJwtStructure(String token);

    boolean validateNameClaim(String value);

    boolean validateRoleClaim(String value);

    boolean validateSeedClaim(String claim);

    boolean validateNumberOfClaims(Map<String, Claim> value);

}

package br.com.itau.jwt.authenticate.infra.components.claims;

public interface ClaimValidatorComponent {

    boolean validateNameClaim(String value);

    boolean validateRoleClaim(String value);

    boolean validateSeedRole(String value);
}

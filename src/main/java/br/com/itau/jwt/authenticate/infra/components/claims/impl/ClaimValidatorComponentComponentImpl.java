package br.com.itau.jwt.authenticate.infra.components.claims.impl;

import br.com.itau.jwt.authenticate.infra.components.claims.ClaimValidatorComponent;
import br.com.itau.jwt.authenticate.infra.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.regex.Pattern;

@Component
public class ClaimValidatorComponentComponentImpl implements ClaimValidatorComponent {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]{1,256}$");
    private static final Set<String> VALID_ROLES = Set.of("Admin", "Member", "External");

    @Override
    public boolean validateNameClaim(String value) {
        return value != null && NAME_PATTERN.matcher(value).matches();
    }

    @Override
    public boolean validateRoleClaim(String value) {
        return VALID_ROLES.contains(value);
    }

    @Override
    public boolean validateSeedRole(String value) {

        if (value == null) {
            return false;
        }

        try {
            return Utils.numberIsPrime(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


package br.com.itau.jwt.authenticate.domain.service;

import br.com.itau.jwt.authenticate.infra.components.claims.ClaimValidatorComponent;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class JwtValidationService {

    private final ClaimValidatorComponent claimValidatorComponent;

    public boolean validateToken(String token) {

        try {

            DecodedJWT decodedJWT = JWT.decode(token);

            return claimValidatorComponent.validateNameClaim(decodedJWT.getClaim("Name").asString()) &&
                    claimValidatorComponent.validateRoleClaim(decodedJWT.getClaim("Role").asString()) &&
                    claimValidatorComponent.validateSeedRole( decodedJWT.getClaim("Seed").asString());

        } catch (SignatureException e) {
            return false;
        }
    }

}

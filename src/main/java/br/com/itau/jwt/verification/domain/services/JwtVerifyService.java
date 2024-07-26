package br.com.itau.jwt.verification.domain.services;

import br.com.itau.jwt.verification.domain.exceptions.JwtInvalidException;
import br.com.itau.jwt.verification.infra.components.validation.JwtValidatorComponent;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtVerifyService {

    private final JwtValidatorComponent jwtValidatorComponent;

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
            log.error("Invalid JWT: {}",e.getMessage());
            throw new JwtInvalidException("Invalid JWT");
        }
    }
}

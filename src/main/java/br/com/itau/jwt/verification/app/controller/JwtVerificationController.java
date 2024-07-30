package br.com.itau.jwt.verification.app.controller;

import br.com.itau.jwt.verification.domain.exceptions.JwtInvalidException;
import br.com.itau.jwt.verification.domain.services.JwtVerifyService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Controller for JWT verification.
 * Provides an endpoint to verify the validity of a JWT according to specific rules.
 *
 * <p>API is documented using OpenAPI/Swagger annotations.</p>
 *
 * @author Phellype Guilherme
 */

@RestController
@RequestMapping("/jwt")
@Tag(name = "JWT verification", description = "Api for JWT verification")
@Slf4j
@RequiredArgsConstructor
public class JwtVerificationController {

    private final JwtVerifyService jwtVerifyService;

    /**
     * Endpoint to verify the validity of a JWT.
     *
     * @param authorizationHeader the JWT passed in the Authorization header
     * @return a ResponseEntity containing a boolean indicating if the JWT is valid or not
     * @throws JwtInvalidException if the JWT does not start with "Bearer "
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JWT is valid"),
            @ApiResponse(responseCode = "401", description = "Invalid JWT")
    })
    @Operation(summary = "Verify JWT", description = "Verifies the validity of a JWT according to specific rules.")
    @GetMapping("/verify")
    @RateLimiter(name = "jwtVerificationService")
    public ResponseEntity<Boolean> verifyJwt(@RequestHeader(value = "Authorization") String authorizationHeader) {

        if (!authorizationHeader.startsWith("Bearer ")) {
           throw new JwtInvalidException("Invalid JWT without flag Bearer");
        }

        if (jwtVerifyService.validateJwt(authorizationHeader)){
            log.info("Valid JWT");
            return new ResponseEntity<>(true, HttpStatus.OK);
        }

        log.error("Invalid JWT");
        return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }
}


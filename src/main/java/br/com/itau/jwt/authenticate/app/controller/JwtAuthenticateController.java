package br.com.itau.jwt.authenticate.app.controller;

import br.com.itau.jwt.authenticate.domain.service.JwtValidationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
@Tag(name = "JWT Verification", description = "Endpoints for JWT verification")
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticateController {

    private final JwtValidationService jwtValidationService;

    @Operation(summary = "Verify JWT", description = "Verifies the validity of a JWT according to specific rules.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JWT is valid"),
            @ApiResponse(responseCode = "401", description = "Invalid JWT")
    })
    @GetMapping("/verify")
    public boolean verifyJwt(@RequestParam String token) {

        if (jwtValidationService.validateToken(token)) {
            log.info("JWT is valid");
            return true;
        } else {
            log.error("Invalid JWT");
            return false;
        }
    }
}


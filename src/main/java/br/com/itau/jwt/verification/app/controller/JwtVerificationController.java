package br.com.itau.jwt.verification.app.controller;

import br.com.itau.jwt.verification.domain.exceptions.JwtInvalidException;
import br.com.itau.jwt.verification.domain.services.JwtVerifyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
@Tag(name = "JWT verification", description = "Api for JWT verification")
@Slf4j
@RequiredArgsConstructor
public class JwtVerificationController {

    private final JwtVerifyService jwtVerifyService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JWT is valid"),
            @ApiResponse(responseCode = "401", description = "Invalid JWT")
    })
    @Operation(summary = "Verify JWT", description = "Verifies the validity of a JWT according to specific rules.")
    @GetMapping("/verify")
    public ResponseEntity<Boolean> verifyJwt(@RequestHeader(value = "Authorization") String authorizationHeader) {

        if (!authorizationHeader.startsWith("Bearer ")) {
           throw new JwtInvalidException("Invalid JWT without flag Bearer");
        }

       return ResponseEntity.ok(jwtVerifyService.validateJwt(authorizationHeader));

    }
}


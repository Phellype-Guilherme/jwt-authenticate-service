package br.com.itau.jwt.verification.infra.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger/OpenAPI documentation.
 * Configures the OpenAPI documentation for the JWT verification service.
 *
 *
 * @author Phellype Guilherme
 */
@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "JWT Bearer";

    /**
     * Configures the OpenAPI documentation for the JWT verification service.
     *
     * <p>This method sets up the title, version, license, and security scheme for the API.</p>
     *
     * @return an {@link OpenAPI} object containing the API documentation configuration
     */
    @Bean
    public OpenAPI jwtVerificationApi() {
        return new OpenAPI()
                .info(new Info().title("JWT verification service").version("Swagger")
                        .license(new License().name("license").url("")))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(
                        new Components()
                                .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                        new SecurityScheme()
                                                .name(SECURITY_SCHEME_NAME)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }

}

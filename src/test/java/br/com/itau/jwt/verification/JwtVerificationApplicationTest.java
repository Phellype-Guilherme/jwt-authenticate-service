package br.com.itau.jwt.verification;

import br.com.itau.jwt.verification.app.controller.JwtVerificationController;
import br.com.itau.jwt.verification.domain.services.JwtVerifyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link JwtVerificationApplication}.
 * Ensures that the application context loads successfully and
 * the required beans are created and injected.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class JwtVerificationApplicationTest {

    @Autowired
    private JwtVerificationController jwtVerificationController;

    @Autowired
    private JwtVerifyService jwtVerifyService;

    /**
     * Test to ensure that the application context loads and
     * the {@link JwtVerificationController} and {@link JwtVerifyService}
     * beans are correctly instantiated and injected.
     */
    @Test
    void contextLoads() {
        assertThat(jwtVerificationController).isNotNull();
        assertThat(jwtVerifyService).isNotNull();
    }
}
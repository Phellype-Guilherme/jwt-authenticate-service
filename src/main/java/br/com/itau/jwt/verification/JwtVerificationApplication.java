package br.com.itau.jwt.verification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main class for the JWT Verification Application.
 *
 * <p>This is the entry point for the Spring Boot application. It initializes and runs the application.</p>
 *
 *
 * @author Phellype Guilherme
 */
@SpringBootApplication
@ComponentScan(basePackages = {"br.com.itau.jwt.verification.app",
		"br.com.itau.jwt.verification.domain",
		"br.com.itau.jwt.verification.infra"})
@Slf4j
public class JwtVerificationApplication {

	/**
	 * Main method to run the Spring Boot application.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(JwtVerificationApplication.class, args);
		log.info("http://localhost:8080/swagger-ui/index.html");
	}

}

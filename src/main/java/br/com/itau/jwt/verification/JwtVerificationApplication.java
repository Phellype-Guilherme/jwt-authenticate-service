package br.com.itau.jwt.verification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.itau.jwt.verification.app",
		"br.com.itau.jwt.verification.domain",
		"br.com.itau.jwt.verification.infra"})
public class JwtVerificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtVerificationApplication.class, args);
	}

}

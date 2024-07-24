package br.com.itau.jwt.authenticate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.itau.jwt.authenticate.app",
		"br.com.itau.jwt.authenticate.domain",
		"br.com.itau.jwt.authenticate.infra"})
public class JwtAuthenticateApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthenticateApplication.class, args);
	}

}

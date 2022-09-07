package com.bravura.finco;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Finco",
		version = "1.0",
		description = "Middleware between finco-nlp and finco-web")
)
public class FincoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FincoApplication.class, args);
	}

}

package br.com.vroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "Vroom API", version = "v1", description = "API do sistema de controle de pátios de motos"))
public class VroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(VroomApplication.class, args);
	}

}

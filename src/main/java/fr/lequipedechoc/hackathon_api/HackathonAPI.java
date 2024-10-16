package fr.lequipedechoc.hackathon_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="Inote API documentation", version ="1.0", description = "Product functionnalities"))
public class HackathonAPI {
    public static void main(String[] args) {
        SpringApplication.run(HackathonAPI.class, args);
    }
}






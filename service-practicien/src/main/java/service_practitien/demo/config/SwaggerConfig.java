package service_practitien.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI praticienAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Service Praticien")
                        .description("API pour la gestion des praticiens du cabinet médical")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Cabinet Médical")
                                .email("contact@cabinet-medical.fr")))
                .servers(List.of(new Server().url("http://localhost:8082")
                        .description("Serveur local")));
    }
}
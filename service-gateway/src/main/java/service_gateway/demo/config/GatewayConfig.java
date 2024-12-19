package service_gateway.demo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("patient_service_fallback",
                        r -> r.path("/api/patients/**")
                                .filters(f -> f.circuitBreaker(config -> config
                                        .setName("patientService")
                                        .setFallbackUri("forward:/fallback/patient")))
                                .uri("http://localhost:8081"))
                .route("praticien_service_fallback",
                        r -> r.path("/api/praticiens/**")
                                .filters(f -> f.circuitBreaker(config -> config
                                        .setName("praticienService")
                                        .setFallbackUri("forward:/fallback/praticien")))
                                .uri("http://localhost:8082"))
                .route("rdv_service_fallback",
                        r -> r.path("/api/rendez-vous/**")
                                .filters(f -> f.circuitBreaker(config -> config
                                        .setName("rdvService")
                                        .setFallbackUri("forward:/fallback/rdv")))
                                .uri("http://localhost:8083"))
                .route("dossier_medical_service_fallback",
                        r -> r.path("/api/dossiers-medicaux/**")
                                .filters(f -> f.circuitBreaker(config -> config
                                        .setName("dossierMedicalService")
                                        .setFallbackUri("forward:/fallback/dossier-medical")))
                                .uri("http://localhost:8084"))
                .build();
    }
}
package service_gateway.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/patient")
    public ResponseEntity<String> patientServiceFallback() {
        return ResponseEntity.ok("Service Patient indisponible. Veuillez réessayer plus tard.");
    }

    @GetMapping("/praticien")
    public ResponseEntity<String> praticienServiceFallback() {
        return ResponseEntity.ok("Service Praticien indisponible. Veuillez réessayer plus tard.");
    }

    @GetMapping("/rdv")
    public ResponseEntity<String> rdvServiceFallback() {
        return ResponseEntity.ok("Service Rendez-vous indisponible. Veuillez réessayer plus tard.");
    }

    @GetMapping("/dossier-medical")
    public ResponseEntity<String> dossierMedicalServiceFallback() {
        return ResponseEntity.ok("Service Dossier Médical indisponible. Veuillez réessayer plus tard.");
    }
}
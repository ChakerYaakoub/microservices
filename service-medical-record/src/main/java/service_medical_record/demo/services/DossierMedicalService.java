package service_medical_record.demo.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import service_medical_record.demo.models.DossierMedical;
import service_medical_record.demo.repositories.DossierMedicalRepository;

import java.util.List;
import java.util.Collections;

@Service
public class DossierMedicalService {

    private static final String DOSSIER_MEDICAL_SERVICE = "dossierMedicalService";

    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = DOSSIER_MEDICAL_SERVICE, fallbackMethod = "getAllDossiersMedicauxFallback")
    public ResponseEntity<List<DossierMedical>> getAllDossiersMedicaux() {
        try {
            List<DossierMedical> dossiers = dossierMedicalRepository.findAll();
            return ResponseEntity.ok(dossiers);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<List<DossierMedical>> getAllDossiersMedicauxFallback(Exception e) {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @CircuitBreaker(name = DOSSIER_MEDICAL_SERVICE, fallbackMethod = "getDossierMedicalByIdFallback")
    public ResponseEntity<DossierMedical> getDossierMedicalById(Long id) {
        try {
            return dossierMedicalRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<DossierMedical> getDossierMedicalByIdFallback(Long id, Exception e) {
        System.out.println("Fallback: Impossible de récupérer le dossier médical " + id);
        return ResponseEntity.notFound().build();
    }

    @CircuitBreaker(name = DOSSIER_MEDICAL_SERVICE, fallbackMethod = "getDossiersMedicauxByPatientFallback")
    @Retry(name = DOSSIER_MEDICAL_SERVICE)
    public ResponseEntity<List<DossierMedical>> getDossiersMedicauxByPatient(Long patientId) {
        try {
            // Vérifier si le patient existe
            ResponseEntity<Object> patientResponse = restTemplate.getForEntity(
                    "http://localhost:8081/api/patients/" + patientId,
                    Object.class);

            if (!patientResponse.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.notFound().build();
            }

            List<DossierMedical> dossiers = dossierMedicalRepository.findByPatientId(patientId);
            return ResponseEntity.ok(dossiers);
        } catch (Exception e) {
            throw e; // Laisser l'exception se propager pour le retry
        }
    }

    public ResponseEntity<List<DossierMedical>> getDossiersMedicauxByPatientFallback(Long patientId, Exception e) {
        System.out.println("Fallback: Impossible de récupérer les dossiers pour le patient " + patientId);
        return ResponseEntity.ok(Collections.emptyList());
    }

    @CircuitBreaker(name = DOSSIER_MEDICAL_SERVICE, fallbackMethod = "getDossiersMedicauxByPraticienFallback")
    @Retry(name = DOSSIER_MEDICAL_SERVICE)
    public ResponseEntity<List<DossierMedical>> getDossiersMedicauxByPraticien(Long praticienId) {
        try {
            ResponseEntity<Object> praticienResponse = restTemplate.getForEntity(
                    "http://localhost:8082/api/praticiens/" + praticienId,
                    Object.class);

            if (!praticienResponse.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.notFound().build();
            }

            List<DossierMedical> dossiers = dossierMedicalRepository.findByPraticienId(praticienId);
            return ResponseEntity.ok(dossiers);
        } catch (Exception e) {
            throw e; // Laisser l'exception se propager pour le retry
        }
    }

    public ResponseEntity<List<DossierMedical>> getDossiersMedicauxByPraticienFallback(Long praticienId, Exception e) {
        System.out.println("Fallback: Impossible de récupérer les dossiers pour le praticien " + praticienId);
        return ResponseEntity.ok(Collections.emptyList());
    }

    @CircuitBreaker(name = DOSSIER_MEDICAL_SERVICE, fallbackMethod = "saveDossierMedicalFallback")
    @Retry(name = DOSSIER_MEDICAL_SERVICE)
    public ResponseEntity<DossierMedical> saveDossierMedical(DossierMedical dossierMedical) {
        try {
            if (!isValidDossierMedical(dossierMedical)) {
                return ResponseEntity.badRequest().build();
            }

            // Vérifier si le patient existe
            ResponseEntity<Object> patientResponse = restTemplate.getForEntity(
                    "http://localhost:8081/api/patients/" + dossierMedical.getPatientId(),
                    Object.class);

            // Vérifier si le praticien existe
            ResponseEntity<Object> praticienResponse = restTemplate.getForEntity(
                    "http://localhost:8082/api/praticiens/" + dossierMedical.getPraticienId(),
                    Object.class);

            if (!patientResponse.getStatusCode().is2xxSuccessful() ||
                    !praticienResponse.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.badRequest().build();
            }

            DossierMedical savedDossier = dossierMedicalRepository.save(dossierMedical);
            return ResponseEntity.ok(savedDossier);
        } catch (Exception e) {
            throw e; // Laisser l'exception se propager pour le retry
        }
    }

    public ResponseEntity<DossierMedical> saveDossierMedicalFallback(DossierMedical dossierMedical, Exception e) {
        System.out.println("Fallback: Impossible de sauvegarder le dossier médical");
        return ResponseEntity.internalServerError().build();
    }

    @CircuitBreaker(name = DOSSIER_MEDICAL_SERVICE, fallbackMethod = "deleteDossierMedicalFallback")
    public ResponseEntity<Void> deleteDossierMedical(Long id) {
        try {
            if (!dossierMedicalRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            dossierMedicalRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<Void> deleteDossierMedicalFallback(Long id, Exception e) {
        System.out.println("Fallback: Impossible de supprimer le dossier médical " + id);
        return ResponseEntity.internalServerError().build();
    }

    private boolean isValidDossierMedical(DossierMedical dossierMedical) {
        return dossierMedical != null
                && dossierMedical.getPatientId() != null
                && dossierMedical.getPraticienId() != null;
    }
}
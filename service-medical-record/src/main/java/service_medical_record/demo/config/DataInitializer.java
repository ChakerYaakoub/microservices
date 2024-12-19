package service_medical_record.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import service_medical_record.demo.models.DossierMedical;
import service_medical_record.demo.repositories.DossierMedicalRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;

    @Autowired
    private RestTemplate restTemplate;

    private boolean validatePatientId(Long patientId) {
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(
                    "http://localhost:8081/api/patients/" + patientId,
                    Object.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            System.out.println("Erreur lors de la validation du patient ID: " + patientId);
            return false;
        }
    }

    private boolean validatePraticienId(Long praticienId) {
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(
                    "http://localhost:8082/api/praticiens/" + praticienId,
                    Object.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            System.out.println("Erreur lors de la validation du praticien ID: " + praticienId);
            return false;
        }
    }

    @Override
    public void run(String... args) {
        // Vérifier si des dossiers médicaux existent déjà
        if (dossierMedicalRepository.count() > 0) {
            return;
        }

        // Créer des dossiers médicaux seulement si les IDs sont valides
        Long[] patientIds = { 1L, 2L, 3L, 11L }; // Test avec un ID invalide (11)
        Long[] praticienIds = { 1L, 2L, 3L };

        for (int i = 0; i < patientIds.length; i++) {
            Long patientId = patientIds[i];
            Long praticienId = praticienIds[i % praticienIds.length];

            // Valider les IDs avant de créer le dossier
            if (!validatePatientId(patientId)) {
                System.out.println("Patient ID invalide: " + patientId + ". Dossier non créé.");
                continue;
            }

            if (!validatePraticienId(praticienId)) {
                System.out.println("Praticien ID invalide: " + praticienId + ". Dossier non créé.");
                continue;
            }

            // Créer le dossier médical si les IDs sont valides
            DossierMedical dossier = new DossierMedical();
            dossier.setPatientId(patientId);
            dossier.setPraticienId(praticienId);
            dossier.setAntecedents("Antécédents du patient " + patientId);
            dossier.setAllergies("Allergies du patient " + patientId);
            dossier.setTraitementsCourants("Traitements du patient " + patientId);
            dossier.setNotes("Notes pour le patient " + patientId);
            dossier.setStatut("ACTIF");

            try {
                dossierMedicalRepository.save(dossier);
                System.out.println("Dossier médical créé pour le patient: " + patientId);
            } catch (Exception e) {
                System.out.println("Erreur lors de la création du dossier pour le patient: " + patientId);
            }
        }
    }
}
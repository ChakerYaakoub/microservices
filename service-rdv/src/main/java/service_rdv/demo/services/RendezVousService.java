package service_rdv.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import service_rdv.demo.models.RendezVous;
import service_rdv.demo.repositories.RendezVousRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RendezVousService {

    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GoogleCalendarService googleCalendarService;

    public ResponseEntity<List<RendezVous>> getAllRendezVous() {
        try {
            List<RendezVous> rendezVous = rendezVousRepository.findAll();
            return ResponseEntity.ok(rendezVous);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<RendezVous> getRendezVousById(Long id) {
        try {
            return rendezVousRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<List<RendezVous>> getRendezVousByPatient(Long patientId) {
        try {
            List<RendezVous> rendezVous = rendezVousRepository.findByPatientId(patientId);
            return ResponseEntity.ok(rendezVous);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<List<RendezVous>> getRendezVousByPraticien(Long praticienId) {
        try {
            List<RendezVous> rendezVous = rendezVousRepository.findByPraticienId(praticienId);
            return ResponseEntity.ok(rendezVous);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<RendezVous> saveRendezVous(RendezVous rendezVous) {
        try {
            if (!isValidRendezVous(rendezVous)) {
                return ResponseEntity.badRequest().build();
            }

            // Vérifier si le patient existe
            ResponseEntity<Object> patientResponse = restTemplate.getForEntity(
                "http://localhost:8081/api/patients/" + rendezVous.getPatientId(), 
                Object.class
            );

            // Vérifier si le praticien existe
            ResponseEntity<Object> praticienResponse = restTemplate.getForEntity(
                "http://localhost:8082/api/praticiens/" + rendezVous.getPraticienId(), 
                Object.class
            );

            if (!patientResponse.getStatusCode().is2xxSuccessful() || 
                !praticienResponse.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.badRequest().build();
            }

            // Générer le lien Google Calendar
            String calendarLink = googleCalendarService.generateGoogleCalendarLink(
                "Rendez-vous médical - " + rendezVous.getMotif(),
                rendezVous.getDateHeure(),
                rendezVous.getDateHeure().plusHours(1), // durée par défaut 1h
                rendezVous.getNotes(),
                "Cabinet médical" // à adapter selon vos besoins
            );
            
            rendezVous.setNotes(rendezVous.getNotes() + "\nLien Calendar: " + calendarLink);
            RendezVous savedRendezVous = rendezVousRepository.save(rendezVous);
            return ResponseEntity.ok(savedRendezVous);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<Void> deleteRendezVous(Long id) {
        try {
            if (!rendezVousRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            rendezVousRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private boolean isValidRendezVous(RendezVous rendezVous) {
        return rendezVous != null
                && rendezVous.getPatientId() != null
                && rendezVous.getPraticienId() != null
                && rendezVous.getDateHeure() != null
                && rendezVous.getDateHeure().isAfter(LocalDateTime.now())
                && rendezVous.getMotif() != null && !rendezVous.getMotif().trim().isEmpty();
    }
} 
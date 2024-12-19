package service_rdv.demo.config;
// juste pour tester
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import service_rdv.demo.models.RendezVous;
import service_rdv.demo.models.Patient;
import service_rdv.demo.models.Praticien;
import service_rdv.demo.repositories.RendezVousRepository;
import service_rdv.demo.services.GoogleCalendarService;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Autowired
    private GoogleCalendarService googleCalendarService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(String... args) {
        // Vérifier si des rendez-vous existent déjà
        if (rendezVousRepository.count() > 0) {
            return; // Ne rien faire si des rendez-vous existent déjà
        }

        // Récupérer tous les patients
        ResponseEntity<List<Patient>> patientsResponse = restTemplate.exchange(
                "http://localhost:8081/api/patients",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Patient>>() {
                });
        List<Patient> patients = patientsResponse.getBody();

        // Récupérer tous les praticiens
        ResponseEntity<List<Praticien>> praticiensResponse = restTemplate.exchange(
                "http://localhost:8082/api/praticiens",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Praticien>>() {
                });
        List<Praticien> praticiens = praticiensResponse.getBody();

        if (patients != null && praticiens != null && !patients.isEmpty() && !praticiens.isEmpty()) {
            // RDV 1: Premier patient avec premier praticien
            RendezVous rdv1 = new RendezVous();
            rdv1.setPatientId(patients.get(0).getId());
            rdv1.setPraticienId(praticiens.get(0).getId());
            rdv1.setDateHeure(LocalDateTime.now().plusDays(1).withHour(9).withMinute(0));
            rdv1.setMotif("Consultation " + praticiens.get(0).getSpecialite());
            rdv1.setStatut("CONFIRME");
            rdv1.setNotes("Première consultation");

            String calendarLink1 = googleCalendarService.generateGoogleCalendarLink(
                    "Consultation " + praticiens.get(0).getSpecialite() + " - Dr " + praticiens.get(0).getNom(),
                    rdv1.getDateHeure(),
                    rdv1.getDateHeure().plusHours(1),
                    rdv1.getNotes(),
                    "Cabinet médical");
            rdv1.setNotes(rdv1.getNotes() + "\nLien Calendar: " + calendarLink1);
            rendezVousRepository.save(rdv1);

            // RDV 2: Deuxième patient avec deuxième praticien
            if (patients.size() > 1 && praticiens.size() > 1) {
                RendezVous rdv2 = new RendezVous();
                rdv2.setPatientId(patients.get(1).getId());
                rdv2.setPraticienId(praticiens.get(1).getId());
                rdv2.setDateHeure(LocalDateTime.now().plusDays(2).withHour(14).withMinute(30));
                rdv2.setMotif("Consultation " + praticiens.get(1).getSpecialite());
                rdv2.setStatut("PLANIFIE");
                rdv2.setNotes("Suivi régulier");

                String calendarLink2 = googleCalendarService.generateGoogleCalendarLink(
                        "Consultation " + praticiens.get(1).getSpecialite() + " - Dr " + praticiens.get(1).getNom(),
                        rdv2.getDateHeure(),
                        rdv2.getDateHeure().plusMinutes(30),
                        rdv2.getNotes(),
                        "Cabinet médical");
                rdv2.setNotes(rdv2.getNotes() + "\nLien Calendar: " + calendarLink2);
                rendezVousRepository.save(rdv2);
            }

            // RDV 3: Troisième patient avec troisième praticien
            if (patients.size() > 2 && praticiens.size() > 2) {
                RendezVous rdv3 = new RendezVous();
                rdv3.setPatientId(patients.get(2).getId());
                rdv3.setPraticienId(praticiens.get(2).getId());
                rdv3.setDateHeure(LocalDateTime.now().plusDays(3).withHour(11).withMinute(0));
                rdv3.setMotif("Consultation " + praticiens.get(2).getSpecialite());
                rdv3.setStatut("CONFIRME");
                rdv3.setNotes("Examen annuel");

                String calendarLink3 = googleCalendarService.generateGoogleCalendarLink(
                        "Consultation " + praticiens.get(2).getSpecialite() + " - Dr " + praticiens.get(2).getNom(),
                        rdv3.getDateHeure(),
                        rdv3.getDateHeure().plusMinutes(45),
                        rdv3.getNotes(),
                        "Cabinet médical");
                rdv3.setNotes(rdv3.getNotes() + "\nLien Calendar: " + calendarLink3);
                rendezVousRepository.save(rdv3);
            }
        }
    }
}

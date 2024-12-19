package service_rdv.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service_rdv.demo.models.RendezVous;
import service_rdv.demo.services.RendezVousService;

import java.util.List;

@RestController
@RequestMapping("/api/rendez-vous")
public class RendezVousController {

    @Autowired
    private RendezVousService rendezVousService;

    @GetMapping
    public ResponseEntity<List<RendezVous>> getAllRendezVous() {
        return rendezVousService.getAllRendezVous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RendezVous> getRendezVousById(@PathVariable Long id) {
        return rendezVousService.getRendezVousById(id);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<RendezVous>> getRendezVousByPatient(@PathVariable Long patientId) {
        return rendezVousService.getRendezVousByPatient(patientId);
    }

    @GetMapping("/praticien/{praticienId}")
    public ResponseEntity<List<RendezVous>> getRendezVousByPraticien(@PathVariable Long praticienId) {
        return rendezVousService.getRendezVousByPraticien(praticienId);
    }

    @PostMapping
    public ResponseEntity<RendezVous> createRendezVous(@RequestBody RendezVous rendezVous) {
        return rendezVousService.saveRendezVous(rendezVous);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RendezVous> updateRendezVous(@PathVariable Long id, @RequestBody RendezVous rendezVous) {
        rendezVous.setId(id);
        return rendezVousService.saveRendezVous(rendezVous);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) {
        return rendezVousService.deleteRendezVous(id);
    }
}
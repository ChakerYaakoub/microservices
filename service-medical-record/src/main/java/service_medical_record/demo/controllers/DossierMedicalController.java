package service_medical_record.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service_medical_record.demo.models.DossierMedical;
import service_medical_record.demo.services.DossierMedicalService;

import java.util.List;

@RestController
@RequestMapping("/api/dossiers-medicaux")
public class DossierMedicalController {

    @Autowired
    private DossierMedicalService dossierMedicalService;

    @GetMapping
    public ResponseEntity<List<DossierMedical>> getAllDossiersMedicaux() {
        return dossierMedicalService.getAllDossiersMedicaux();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DossierMedical> getDossierMedicalById(@PathVariable Long id) {
        return dossierMedicalService.getDossierMedicalById(id);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<DossierMedical>> getDossiersMedicauxByPatient(@PathVariable Long patientId) {
        return dossierMedicalService.getDossiersMedicauxByPatient(patientId);
    }

    @GetMapping("/praticien/{praticienId}")
    public ResponseEntity<List<DossierMedical>> getDossiersMedicauxByPraticien(@PathVariable Long praticienId) {
        return dossierMedicalService.getDossiersMedicauxByPraticien(praticienId);
    }

    @PostMapping
    public ResponseEntity<DossierMedical> createDossierMedical(@RequestBody DossierMedical dossierMedical) {
        return dossierMedicalService.saveDossierMedical(dossierMedical);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DossierMedical> updateDossierMedical(@PathVariable Long id,
            @RequestBody DossierMedical dossierMedical) {
        dossierMedical.setId(id);
        return dossierMedicalService.saveDossierMedical(dossierMedical);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDossierMedical(@PathVariable Long id) {
        return dossierMedicalService.deleteDossierMedical(id);
    }
}
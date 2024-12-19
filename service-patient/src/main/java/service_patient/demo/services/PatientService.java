package service_patient.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import service_patient.demo.models.Patient;
import service_patient.demo.repositories.PatientRepository;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public ResponseEntity<List<Patient>> getAllPatients() {
        try {
            List<Patient> patients = patientRepository.findAll();
            return ResponseEntity.ok(patients);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<Patient> getPatientById(Long id) {
        try {
            return patientRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<Patient> savePatient(Patient patient) {
        try {
            if (!isValidPatient(patient)) {
                return ResponseEntity.badRequest().build();
            }
            Patient savedPatient = patientRepository.save(patient);
            return ResponseEntity.ok(savedPatient);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<Void> deletePatient(Long id) {
        try {
            if (!patientRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            patientRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private boolean isValidPatient(Patient patient) {
        return patient != null
                && patient.getNom() != null && !patient.getNom().trim().isEmpty()
                && patient.getPrenom() != null && !patient.getPrenom().trim().isEmpty()
                && patient.getNumeroSecu() != null && patient.getNumeroSecu().matches("\\d{15}")
                && patient.getTelephone() != null && patient.getTelephone().matches("^0[67]\\d{8}$");
    }
}
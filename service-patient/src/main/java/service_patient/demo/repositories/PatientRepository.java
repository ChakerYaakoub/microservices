package service_patient.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import service_patient.demo.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
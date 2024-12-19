package service_medical_record.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import service_medical_record.demo.models.DossierMedical;
import java.util.List;

public interface DossierMedicalRepository extends JpaRepository<DossierMedical, Long> {
    List<DossierMedical> findByPatientId(Long patientId);

    List<DossierMedical> findByPraticienId(Long praticienId);

    DossierMedical findByPatientIdAndStatut(Long patientId, String statut);
}
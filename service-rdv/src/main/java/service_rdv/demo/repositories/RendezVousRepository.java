package service_rdv.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import service_rdv.demo.models.RendezVous;
import java.time.LocalDateTime;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByPatientId(Long patientId);
    List<RendezVous> findByPraticienId(Long praticienId);
    List<RendezVous> findByDateHeureBetween(LocalDateTime debut, LocalDateTime fin);
}
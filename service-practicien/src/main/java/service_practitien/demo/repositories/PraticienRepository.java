package service_practitien.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import service_practitien.demo.models.Praticien;

public interface PraticienRepository extends JpaRepository<Praticien, Long> {
}
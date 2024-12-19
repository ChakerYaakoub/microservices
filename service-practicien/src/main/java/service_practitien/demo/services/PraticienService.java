package service_practitien.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import service_practitien.demo.models.Praticien;
import service_practitien.demo.repositories.PraticienRepository;

import java.util.List;

@Service
public class PraticienService {

    @Autowired
    private PraticienRepository praticienRepository;

    public ResponseEntity<List<Praticien>> getAllPraticiens() {
        try {
            List<Praticien> praticiens = praticienRepository.findAll();
            return ResponseEntity.ok(praticiens);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<Praticien> getPraticienById(Long id) {
        try {
            return praticienRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<Praticien> savePraticien(Praticien praticien) {
        try {
            if (!isValidPraticien(praticien)) {
                return ResponseEntity.badRequest().build();
            }
            Praticien savedPraticien = praticienRepository.save(praticien);
            return ResponseEntity.ok(savedPraticien);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<Void> deletePraticien(Long id) {
        try {
            if (!praticienRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            praticienRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private boolean isValidPraticien(Praticien praticien) {
        return praticien != null
                && praticien.getNom() != null && !praticien.getNom().trim().isEmpty()
                && praticien.getPrenom() != null && !praticien.getPrenom().trim().isEmpty()
                && praticien.getSpecialite() != null && !praticien.getSpecialite().trim().isEmpty()
                && praticien.getTelephone() != null && praticien.getTelephone().matches("^0[67]\\d{8}$")
                && praticien.getEmail() != null
                && praticien.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}
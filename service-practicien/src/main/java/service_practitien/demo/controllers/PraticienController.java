package service_practitien.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service_practitien.demo.models.Praticien;
import service_practitien.demo.services.PraticienService;

import java.util.List;

@RestController
@RequestMapping("/api/praticiens")
public class PraticienController {

    @Autowired
    private PraticienService praticienService;

    @GetMapping
    public ResponseEntity<List<Praticien>> getAllPraticiens() {
        return praticienService.getAllPraticiens();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Praticien> getPraticienById(@PathVariable Long id) {
        return praticienService.getPraticienById(id);
    }

    @PostMapping
    public ResponseEntity<Praticien> createPraticien(@RequestBody Praticien praticien) {
        return praticienService.savePraticien(praticien);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Praticien> updatePraticien(@PathVariable Long id, @RequestBody Praticien praticien) {
        praticien.setId(id);
        return praticienService.savePraticien(praticien);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePraticien(@PathVariable Long id) {
        return praticienService.deletePraticien(id);
    }
}
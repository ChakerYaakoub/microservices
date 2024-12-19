package service_rdv.demo.models;

import java.time.LocalDate;

public class Patient {
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String numeroSecu;
    private String telephone;
    private String email;
    private String adresse;
    private String codePostal;
    private String ville;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Autres getters et setters...
}
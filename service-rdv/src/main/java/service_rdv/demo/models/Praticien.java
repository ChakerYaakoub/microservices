package service_rdv.demo.models;

public class Praticien {
    private Long id;
    private String nom;
    private String prenom;
    private String specialite;
    private String telephone;
    private String email;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Autres getters et setters...
}
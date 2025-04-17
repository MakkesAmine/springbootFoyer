package tn.esprit.tpfoyer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity

public class Reservation {
    @Id
    private String idReservaion;
    private Date anneeUniversitaire;
    private Boolean estValide;
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore // éviter la boucle infinie
    @ToString.Exclude
    private Set<Etudiant> etudiants;
    @ManyToOne
    @JoinColumn(name = "chambre_id") // This will create a foreign key column in Reservation
    private Chambre chambre;

    public Reservation() {
    }

    public Reservation(String idReservaion, Date anneeUniversitaire, Boolean estValide, Set<Etudiant> etudiants) {
        this.idReservaion = idReservaion;
        this.anneeUniversitaire = anneeUniversitaire;
        this.estValide = estValide;
        this.etudiants = etudiants;
    }

    public String getIdReservaion() {
        return idReservaion;
    }

    public void setIdReservaion(String idReservaion) {
        this.idReservaion = idReservaion;
    }

    public Date getAnneeUniversitaire() {
        return anneeUniversitaire;
    }

    public void setAnneeUniversitaire(Date anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
    }

    public Boolean getEstValide() {
        return estValide;
    }

    public void setEstValide(Boolean estValide) {
        this.estValide = estValide;
    }

    public Set<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(Set<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }
    public void setChambre(Chambre chambre) {this.chambre = chambre;}
    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation='" + idReservaion + '\'' +
                ", anneeUniversitaire=" + anneeUniversitaire +
                ", estValide=" + estValide +
                '}';
    }

}
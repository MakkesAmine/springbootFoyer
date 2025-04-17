package tn.esprit.tpfoyer.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity


public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChambre;
    private Long numeroChambre;
    @Enumerated(EnumType.STRING)
    private TypeChambre typeC;
    @ManyToOne
    Bloc bloc;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore // éviter la boucle infinie
    @ToString.Exclude
    private Set<Reservation> Reservations;

    public Chambre() {
    }

    public Chambre(Long idChambre, Long numeroChambre, TypeChambre typeC, Bloc bloc, Set<Reservation> reservations) {
        this.idChambre = idChambre;
        this.numeroChambre = numeroChambre;
        this.typeC = typeC;
        this.bloc = bloc;
        Reservations = reservations;
    }

    public Long getIdChambre() {
        return idChambre;
    }

    public Long getNumeroChambre() {
        return numeroChambre;
    }

    public TypeChambre getTypeC() {
        return typeC;
    }

    public Bloc getBloc() {
        return bloc;
    }

    public Set<Reservation> getReservations() {
        return Reservations;
    }

    public void setIdChambre(Long idChambre) {
        this.idChambre = idChambre;
    }

    public void setNumeroChambre(Long numeroChambre) {
        this.numeroChambre = numeroChambre;
    }

    public void setTypeC(TypeChambre typeC) {
        this.typeC = typeC;
    }

    public void setBloc(Bloc bloc) {
        this.bloc = bloc;
    }

    public void setReservations(Set<Reservation> reservations) {
        Reservations = reservations;
    }
}

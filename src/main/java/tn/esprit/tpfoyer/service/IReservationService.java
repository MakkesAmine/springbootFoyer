package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.entity.Reservation;

import java.util.List;

public interface IReservationService {
    public List<Reservation> retrieveAllReservations();
    public Reservation retrieveReservation(String reservationId);
    public Reservation addReservation(Reservation r);
    public void removeReservation(String reservationId);
    public Reservation modifyReservation(Reservation reservation);

    void assignEtudiantToRservation(String idReservation, Long idEtudiant);

    Reservation addReservationWithEtudiants(Reservation reservation, List<Long> idEtudiants);

    void removeEtudiantsFromReservation(String idReservation, List<Long> idEtudiants);

    void removeReservationAndUnassignEtudiants(String idReservation);

    void removeEtudiantFromReservation(String idReservation, Long idEtudiant);
}

package tn.esprit.tpfoyer.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Override
    public List<Reservation> retrieveAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation retrieveReservation(String reservationId) {
        return reservationRepository.findById(reservationId).get();
    }

    @Override
    public Reservation addReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public void removeReservation(String reservationId) {
        reservationRepository.deleteById(reservationId);

    }


    public void assignEtudiantToRservation(String idReservaion, Long idEtudiant) {
        Reservation reservation = reservationRepository.findById(idReservaion).get();
        Etudiant etudiant = etudiantRepository.findById(idEtudiant).get();
        // on setle fils dans le parent  :
        reservation.getEtudiants().add(etudiant);
        reservationRepository.save(reservation);
    }

    public Reservation addReservationWithEtudiants(Reservation reservation, List<Long> idEtudiants) {
        List<Etudiant> etudiants = etudiantRepository.findAllById(idEtudiants);
        reservation.setEtudiants(new HashSet<>(etudiants));
        return reservationRepository.save(reservation);
    }
    public void removeEtudiantsFromReservation(String idReservation, List<Long> idEtudiants) {
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new EntityNotFoundException("Réservation non trouvée"));
        List<Etudiant> etudiants = etudiantRepository.findAllById(idEtudiants);

        reservation.getEtudiants().removeAll(etudiants);
        reservationRepository.save(reservation);
    }
    public void removeReservationAndUnassignEtudiants(String idReservation) {
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new EntityNotFoundException("Réservation non trouvée"));

        // Désaffecter tous les étudiants avant suppression
        for (Etudiant etudiant : reservation.getEtudiants()) {
            etudiant.getReservations().remove(reservation);
        }

        reservationRepository.delete(reservation);
    }
    public void removeEtudiantFromReservation(String idReservation, Long idEtudiant) {
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new EntityNotFoundException("Réservation non trouvée"));
        Etudiant etudiant = etudiantRepository.findById(idEtudiant)
                .orElseThrow(() -> new EntityNotFoundException("Étudiant non trouvé"));

        reservation.getEtudiants().remove(etudiant);
        reservationRepository.save(reservation);
    }



    @Override
    public Reservation modifyReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}

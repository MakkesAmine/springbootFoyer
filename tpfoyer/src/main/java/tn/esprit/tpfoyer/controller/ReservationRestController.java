package tn.esprit.tpfoyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.service.IChambreService;
import tn.esprit.tpfoyer.service.IReservationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationRestController {
    @Autowired
    IReservationService reservationService;
    @Operation(description = "Récupérer toutes les réservations existantes.")
    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> getReservations() {
        List<Reservation> listReservations = reservationService.retrieveAllReservations();
        return listReservations;
    }

    @Operation(description = "Récupérer une réservation spécifique par son ID.")
    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") String resId) {
        Reservation reservation = reservationService.retrieveReservation(resId);
        return reservation;
    }


    @Operation(description = "Ajouter une nouvelle réservation sans étudiants.")
    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation c) {
        Reservation reservation = reservationService.addReservation(c);
        return reservation;
    }

    @Operation(description = "Supprimer une réservation par son ID.")
    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") String resId) {
        reservationService.removeReservation(resId);
    }

    @Operation(description = "Modifier une réservation existante.")
    @PutMapping("/modify-reservation")
    public Reservation modifyReservation(@RequestBody Reservation r) {
        Reservation reservation = reservationService.modifyReservation(r);
        return reservation;
    }

    @Operation(description = "Affecter un étudiant spécifique à une réservation donnée.")
    @PutMapping("/affecter-etudiant-a-reservation/{idReservation}/{idEtudiant}")
    public void assignEtudiantToReservation(@PathVariable("idReservation") String idReservation,
                                            @PathVariable("idEtudiant") Long idEtudiant) {
        reservationService.assignEtudiantToRservation(idReservation, idEtudiant);
    }

    @Operation(description = "Créer une réservation avec une liste d'étudiants associée dès la création.")
    @PostMapping("/add-reservation-with-etudiants")
    public Reservation addReservationWithEtudiants(@RequestBody Reservation reservation, @RequestParam List<Long> idEtudiants) {
        return reservationService.addReservationWithEtudiants(reservation, idEtudiants);
    }


    @Operation(description = "Désaffecter un étudiant spécifique d'une réservation.")
    @PutMapping("/desaffecter-etudiants/{idReservation}")
    public void removeEtudiantsFromReservation(@PathVariable String idReservation,
                                               @RequestBody List<Long> idEtudiants) {
        reservationService.removeEtudiantsFromReservation(idReservation, idEtudiants);
    }


    @Operation(description = "Désaffecter plusieurs étudiants d'une réservation donnée.")
    @DeleteMapping("/remove-reservation-unassign/{idReservation}")
    public void removeReservationAndUnassignEtudiants(@PathVariable String idReservation) {
        reservationService.removeReservationAndUnassignEtudiants(idReservation);
    }


    @Operation(description = "Supprimer une réservation et désaffecter tous les étudiants qui y sont liés.")
    @PutMapping("/desaffecter-etudiant/{idReservation}/{idEtudiant}")
    public void removeEtudiantFromReservation(@PathVariable String idReservation,
                                              @PathVariable Long idEtudiant) {
        reservationService.removeEtudiantFromReservation(idReservation, idEtudiant);
    }


}

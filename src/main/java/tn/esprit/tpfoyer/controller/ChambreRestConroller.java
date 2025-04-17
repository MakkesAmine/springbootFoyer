package tn.esprit.tpfoyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.service.IChambreService;

import java.util.List;
@Tag(name = "Gestion Chambre")
@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
public class ChambreRestConroller {
    @Autowired
    IChambreService chambreService;

    // http://localhost:8089/tpfoyer/chambre/retrieve-all-chambres
    @Operation(description = "récupérer toutes les chambres de la base de données")

    @GetMapping("/retrieve-all-chambres")
    public List<Chambre> getChambres() {
        List<Chambre> listChambres = chambreService.retrieveAllChambres();
        return listChambres;
    }

    // http://localhost:8089/tpfoyer/chambre/retrieve-chambre/8
    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chId) {
        Chambre chambre = chambreService.retrieveChambre(chId);
        return chambre;
    }

    // http://localhost:8089/tpfoyer/chambre/add-chambre
    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre c) {
        Chambre chambre = chambreService.addChambre(c);
        return chambre;
    }

    // http://localhost:8089/tpfoyer/chambre/remove-chambre/{chambre-id}
    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") Long chId) {
        chambreService.removeChambre(chId);
    }

    // http://localhost:8089/tpfoyer/chambre/modify-chambre
    @PutMapping("/modify-chambre")
    public Chambre modifyChambre(@RequestBody Chambre c) {
        Chambre chambre = chambreService.modifyChambre(c);
        return chambre;
    }

    @Operation(description = "Créer une Chambre avec ses Réservations en même temps.")
    @PostMapping("/add-chambre-with-reservations")
    public Chambre addChambreWithReservations(@RequestBody Chambre chambre) {
        return chambreService.addChambreWithReservations(chambre);
    }

    @Operation(description = "Réserver une chambre en affectant une réservation existante")
    @PutMapping("/reserver-chambre/{chambreId}/{reservationId}")
    public void reserverChambre(@PathVariable Long chambreId, @PathVariable String reservationId) {
        chambreService.reserverChambre(chambreId, reservationId);
    }

    @Operation(description = "Désaffecter une réservation d'une chambre")
    @PutMapping("/desaffecter-reservation/{chambreId}/{reservationId}")
    public void removeReservationFromChambre(@PathVariable Long chambreId, @PathVariable String reservationId) {
        chambreService.removeReservationFromChambre(chambreId, reservationId);
    }

    @GetMapping("/retrieve-chambre par type/{type-chambre}")
    public List<Chambre> retrieveChambreParType(@PathVariable("type-chambre") TypeChambre tch) {
        List<Chambre> listchambre = chambreService.trouverChambreByType(tch);
        return listchambre;
    }
    @GetMapping("/etudiant/{cin}")
    public List<Chambre> getChambresParCin(@PathVariable Long cin) {
        return chambreService.getChambresReserveesParEtudiant(cin);
    }
}



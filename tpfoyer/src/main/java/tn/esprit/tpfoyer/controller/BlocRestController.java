package tn.esprit.tpfoyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.service.IBlocService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bloc")
public class BlocRestController {

    @Autowired
    IBlocService blocService;

    @Operation(description = "Récupérer tous les Blocs.")
    @GetMapping("/retrieve-all-blocs")
    public List<Bloc> getBlocs() {
        return blocService.retrieveAllBloc();
    }

    @Operation(description = "Récupérer un Bloc par son ID.")
    @GetMapping("/retrieve-bloc/{bloc-id}")
    public Bloc retrieveBloc(@PathVariable("bloc-id") Long blocId) {
        return blocService.retrieveBloc(blocId);
    }

    @Operation(description = "Ajouter un Bloc.")
    @PostMapping("/add-bloc")
    public Bloc addBloc(@RequestBody Bloc bloc) {
        return blocService.addBloc(bloc);
    }

    @Operation(description = "Supprimer un Bloc par son ID.")
    @DeleteMapping("/remove-bloc/{bloc-id}")
    public void removeBloc(@PathVariable("bloc-id") Long blocId) {
        blocService.removeBloc(blocId);
    }

    @Operation(description = "Modifier un Bloc.")
    @PutMapping("/modify-bloc")
    public Bloc modifyBloc(@RequestBody Bloc bloc) {
        return blocService.modifyBloc(bloc);
    }

    @Operation(description = "Créer un Bloc avec son Foyer en même temps.")
    @PostMapping("/add-bloc-with-foyer")
    public Bloc addBlocWithFoyer(@RequestBody Bloc bloc) {
        return blocService.addBlocWithFoyer(bloc);
    }

    @Operation(description = "Affecter un Bloc à un Foyer existant.")
    @PutMapping("/affecter-bloc-a-foyer/{blocId}/{foyerId}")
    public void assignBlocToFoyer(@PathVariable Long blocId, @PathVariable Long foyerId) {
        blocService.assignBlocToFoyer(blocId, foyerId);
    }

    @Operation(description = "Désaffecter un Bloc de son Foyer.")
    @PutMapping("/desaffecter-bloc/{blocId}")
    public void removeBlocFromFoyer(@PathVariable Long blocId) {
        blocService.removeBlocFromFoyer(blocId);
    }


    // Endpoint GET pour récupérer les blocs sans foyer
    @GetMapping("/sansFoyer")
    public List<Bloc> getBlocsSansFoyer() {
        return blocService.getBlocsSansFoyer();
    }
}

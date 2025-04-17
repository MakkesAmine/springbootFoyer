package tn.esprit.tpfoyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.service.IUniversiteService;
import tn.esprit.tpfoyer.service.UniversiteServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteRestController {
    @Autowired
    IUniversiteService universiteService;

    @GetMapping("/retrieve-all-universites")
    public List<Universite> getUniversites() {
        List<Universite> listUniversites = universiteService.retrieveAllUniversites();
        return listUniversites;
    }

    @GetMapping("/retrieve-universite/{universite-id}")
    public Universite retrieveUniversite(@PathVariable("universite-id") Long uniId) {
        Universite universite = universiteService.retrieveUniversite(uniId);
        return universite;
    }

    @PostMapping("/add-universite")
    public Universite addUniversite(@RequestBody Universite u) {
        Universite universite = universiteService.addUniversite(u);
        return universite;
    }

    @DeleteMapping("/remove-universite/{universite-id}")
    public void removeUniversite(@PathVariable("universite-id") Long uniId) {
        universiteService.removeUniversite(uniId);
    }

    @PutMapping("/modify-universite")
    public Universite modifyUniversite(@RequestBody Universite u) {
        Universite universite = universiteService.modifyUniversite(u);
        return universite;
    }
    @PostMapping("/ajouter-universite-et-foyer")
    public Universite addUniversiteAndFoyer(@RequestBody Universite u) {
        Universite universite = universiteService.addUniversiteAndFoyer(u);
        return universite;
    }


    @PutMapping("/affecter-Universite-a-Foyer/{idUniversite}/{idFoyer}")
    public void assignFoyerToUni(@PathVariable("idUniversite") Long idUniversite,
                                 @PathVariable("idFoyer") Long idFoyer) {
        universiteService.assignFoyerToUni(idUniversite, idFoyer); // Appel de la méthode via l'instance injectée
    }
    @PostMapping("/creer-universite-et-affecter-universite-foyer-a-universite/{foyer-id}")
    public Universite creerFoyerEtAffecterUniversite(@RequestBody Universite u, @PathVariable("foyer-id")Long idFoyer) {
        return universiteService.addUniversiteAndAssignUniversiteToFoyer(u, idFoyer);
    }
    @PutMapping("/desaffecter-foyer/{universite-id}")
    public void DesaffecterFoyerDeuniversite(@PathVariable("universite-id") Long idUniversite){
        universiteService.DesaffecterFoyerFromUniversite(idUniversite);
    }
    @GetMapping("/avec-blocs-sup-100")
    public List<Universite> getUniversitesAvecBlocCapaciteSuperieure100() {
        return universiteService.getUniversitesAvecBlocsCapaciteSup100();
    }
}

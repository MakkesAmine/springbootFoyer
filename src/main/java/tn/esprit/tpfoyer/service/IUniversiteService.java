package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Universite;

import java.util.List;

public interface IUniversiteService{
    public Universite AddUniversiteAndFoyer(Universite universite);

    public List<Universite> retrieveAllUniversites();
    public Universite retrieveUniversite(Long universiteId);
    public Universite addUniversite(Universite u);

    void removeUniversite(Long universiteId);

    public Universite modifyUniversite(Universite universite);
    public Universite addUniversiteAndFoyer(Universite u) ;
    public void assignFoyerToUni(Long uniId, Long foyerId) ;

    Universite addUniversiteAndAssignUniversiteToFoyer(Universite universite, Long idFoyer);

    Universite DesaffecterFoyerFromUniversite(Long idUniversite);

    List<Universite> getUniversitesAvecBlocsCapaciteSup100();

    public interface UniversiteService {
        List<Universite> getUniversitesAvecBlocsCapaciteSup100();
    }
}

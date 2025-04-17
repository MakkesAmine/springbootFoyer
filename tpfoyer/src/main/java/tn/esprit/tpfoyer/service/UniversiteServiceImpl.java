package tn.esprit.tpfoyer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.repository.UniversiteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversiteServiceImpl implements IUniversiteService{
    @Autowired
    UniversiteRepository universiteRepository;
    @Autowired
    FoyerRepository foyerRepository;


    public void assignFoyerToUni(Long idUniversite, Long idFoyer) {
        Universite uni = universiteRepository.findById(idUniversite).get();
        Foyer f= foyerRepository.findById(idFoyer).get();
        // on set le fils dans le parent :
        uni.setFoyer(f);
        universiteRepository.save(uni);
    }




    @Override
    public Universite AddUniversiteAndFoyer(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite retrieveUniversite(Long universiteId) {
        return universiteRepository.findById(universiteId).get();
    }

    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }



    @Override
    public void removeUniversite(Long universiteId) {
        universiteRepository.deleteById(universiteId);

    }

    @Override
    public Universite modifyUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }


    
    
    
    
    @Override
    public Universite addUniversiteAndFoyer(Universite u) {
        return universiteRepository.save(u);
    }
    @Override
    public Universite addUniversiteAndAssignUniversiteToFoyer(Universite universite, Long idFoyer) {
        Foyer foyer = foyerRepository.findById(idFoyer).get();
        universite.setFoyer(foyer);
        return universiteRepository.save(universite);
    }
    @Override
    public Universite DesaffecterFoyerFromUniversite(Long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).get();
        universite.setFoyer(null);
        return universiteRepository.save(universite);
    }

    @Override
    public List<Universite> getUniversitesAvecBlocsCapaciteSup100() {
        return universiteRepository.findUniversitesAvecBlocCapaciteSuperieure100();
    }
}

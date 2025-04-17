package tn.esprit.tpfoyer.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlocServiceImpl implements IBlocService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(BlocServiceImpl.class);


    @Autowired
    BlocRepository blocRepository;
    @Autowired
    FoyerRepository foyerRepository;

    @Override
    @Scheduled(fixedRate = 60000) // Exécution toutes les 60 secondes (1 minute)
    public List<Bloc> retrieveAllBlocs() {
        List<Bloc> blocs = blocRepository.findAll();
        log.info("Liste des Blocs: {}", blocs);
        return blocs;
    }
    @Override
    public Bloc retrieveBloc(Long blocId) {
        return blocRepository.findById(blocId)
                .orElseThrow(() -> new EntityNotFoundException("Bloc non trouvé avec l'ID : " + blocId));
    }

    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public void removeBloc(Long blocId) {
        if (!blocRepository.existsById(blocId)) {
            throw new EntityNotFoundException("Bloc non trouvé avec l'ID : " + blocId);
        }
        blocRepository.deleteById(blocId);
    }

    @Override
    public Bloc modifyBloc(Bloc bloc) {
        if (!blocRepository.existsById(bloc.getIdBloc())) {
            throw new EntityNotFoundException("Bloc non trouvé avec l'ID : " + bloc.getIdBloc());
        }
        return blocRepository.save(bloc);
    }

    /** Cas 1 : Créer un Bloc et un nouveau Foyer en même temps */
    public Bloc addBlocWithFoyer(Bloc bloc) {
        // Si le foyer n'existe pas, nous l'ajoutons
        if (bloc.getFoyer() != null) {
            Foyer foyer = foyerRepository.save(bloc.getFoyer()); // Ajout ou mise à jour du foyer
            bloc.setFoyer(foyer); // Lier le foyer au bloc
        }
        return blocRepository.save(bloc); // Ajouter le bloc
    }

    /** Cas 2 : Affecter un Bloc à un Foyer existant */
    public void assignBlocToFoyer(Long blocId, Long foyerId) {
        Bloc bloc = blocRepository.findById(blocId)
                .orElseThrow(() -> new EntityNotFoundException("Bloc non trouvé avec l'ID : " + blocId));
        Foyer foyer = foyerRepository.findById(foyerId)
                .orElseThrow(() -> new EntityNotFoundException("Foyer non trouvé avec l'ID : " + foyerId));

        bloc.setFoyer(foyer);
        blocRepository.save(bloc);
    }

    /** Cas 3 : Désaffecter un Bloc de son Foyer */
    public void removeBlocFromFoyer(Long blocId) {
        Bloc bloc = blocRepository.findById(blocId)
                .orElseThrow(() -> new EntityNotFoundException("Bloc non trouvé avec l'ID : " + blocId));

        bloc.setFoyer(null);
        blocRepository.save(bloc);
    }

    @Override
    public List<Bloc> getBlocsSansFoyer() {
        return blocRepository.findAllByFoyerIsNull();
    }

}

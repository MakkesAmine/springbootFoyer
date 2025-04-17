package tn.esprit.tpfoyer.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
//@Slf4j
public class ChambreServiceImpl implements IChambreService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ChambreServiceImpl.class);
    @Autowired
    ChambreRepository chambreRepository;
    ReservationRepository reservationRepository;


//    @Override
//    public List<Chambre> retrieveAllChambres() {
//        return chambreRepository.findAll();
//    }



    @Scheduled(fixedDelay = 10000) // 10000 millisecondes
    public List<Chambre> retrieveAllChambres() {
        List<Chambre> listC = chambreRepository.findAll();
        for (Chambre c : listC) {
            log.info("Chambre :" + c.toString());
        }
        return listC;
    }

    @Override
    public Chambre retrieveChambre(Long chambreId) {
        return chambreRepository.findById(chambreId).get();
    }

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public void removeChambre(Long chambreId) {
        chambreRepository.deleteById(chambreId);

    }
    @Override
    public Chambre addChambreWithReservations(Chambre chambre) {
        if (chambre.getReservations() != null) {
            for (Reservation reservation : chambre.getReservations()) {
                reservation.setChambre(chambre); // Lier la réservation à la chambre
            }
        }
        return chambreRepository.save(chambre); // Sauvegarde en cascade grâce à cascade = CascadeType.ALL
    }
    @Override
    public void reserverChambre(Long chambreId, String reservationId) {
        // Récupérer la chambre et la réservation depuis la base de données
        Chambre chambre = chambreRepository.findById(chambreId)
                .orElseThrow(() -> new EntityNotFoundException("Chambre non trouvée avec l'ID : " + chambreId));

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Réservation non trouvée avec l'ID : " + reservationId));

        // Associer la réservation à la chambre
        chambre.getReservations().add(reservation);

        // Enregistrer la chambre mise à jour
        chambreRepository.save(chambre);
    }
    @Override
    public void removeReservationFromChambre(Long chambreId, String reservationId) {
        // Récupérer la chambre et la réservation depuis la base de données
        Chambre chambre = chambreRepository.findById(chambreId)
                .orElseThrow(() -> new EntityNotFoundException("Chambre non trouvée avec l'ID : " + chambreId));

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Réservation non trouvée avec l'ID : " + reservationId));

        // Désaffecter la réservation de la chambre
        chambre.getReservations().remove(reservation);

        // Enregistrer la chambre mise à jour
        chambreRepository.save(chambre);
    }

    @Override

    public Chambre modifyChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    public List<Chambre> trouverChambreByType(TypeChambre tch) {
        return chambreRepository.findAllByTypeC(tch);
    }
    @Override
    public List<Chambre> getChambresReserveesParEtudiant(Long cin) {
        return chambreRepository.findChambresByEtudiantCin(cin);
    }
}



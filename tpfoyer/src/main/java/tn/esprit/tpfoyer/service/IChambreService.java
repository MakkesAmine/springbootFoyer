package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;

import java.util.List;

public interface IChambreService {
    public List<Chambre> retrieveAllChambres();
    public Chambre retrieveChambre(Long chambreId);
    public Chambre addChambre(Chambre c);
    public void removeChambre(Long chambreId);

    Chambre addChambreWithReservations(Chambre chambre);

    void reserverChambre(Long chambreId, String reservationId);

    void removeReservationFromChambre(Long chambreId, String reservationId);

    public Chambre modifyChambre(Chambre chambre);
    public List<Chambre> trouverChambreByType(TypeChambre tch);
    List<Chambre> getChambresReserveesParEtudiant(Long cin);
}


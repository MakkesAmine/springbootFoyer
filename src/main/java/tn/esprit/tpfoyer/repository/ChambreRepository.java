package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    List<Chambre> findAllByTypeC(TypeChambre tch);
    @Query("SELECT DISTINCT ch FROM Chambre ch " +
            "JOIN ch.Reservations r " +
            "JOIN r.etudiants e " +
            "WHERE e.cin = :cin")
    List<Chambre> findChambresByEtudiantCin(@Param("cin") Long cin);

}

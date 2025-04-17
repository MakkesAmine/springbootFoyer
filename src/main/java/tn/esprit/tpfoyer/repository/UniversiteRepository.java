package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Universite;

import java.util.List;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Long> {
    @Query("SELECT DISTINCT u FROM Universite u " +
            "JOIN u.foyer f " +
            "JOIN f.Blocs b " +
            "WHERE b.capaciteBloc >= 100")
    List<Universite> findUniversitesAvecBlocCapaciteSuperieure100();
}

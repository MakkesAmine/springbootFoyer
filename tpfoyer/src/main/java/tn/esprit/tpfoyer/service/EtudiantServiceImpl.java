package tn.esprit.tpfoyer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService {
    @Autowired
    EtudiantRepository etudiantRepository;

    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant retrieveEtudiant(Long etudiantId) {
        return etudiantRepository.findById(etudiantId).get();
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public void removeEtudiant(Long EtudiantId) {
        etudiantRepository.deleteById(EtudiantId);


    }








    @Override
    public Etudiant modifyEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }


    @Override
    public List<Etudiant> getEtudiantsParEcoleEtDate(String nomEcole, Date date) {
        return etudiantRepository.findByEcoleAndDateNaissanceAfter(nomEcole, date);
    }

}


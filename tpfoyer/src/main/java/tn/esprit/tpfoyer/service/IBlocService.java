package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Bloc;


import java.util.List;

public interface IBlocService {
    public List<Bloc> retrieveAllBloc();
    public Bloc retrieveBloc(Long blocId);
    public Bloc addBloc(Bloc b);
    public void removeBloc(Long BlocId);
    public Bloc modifyBloc(Bloc bloc);

    Bloc addBlocWithFoyer(Bloc bloc);

    void assignBlocToFoyer(Long blocId, Long foyerId);

    void removeBlocFromFoyer(Long blocId);

    List<Bloc> retrieveAllBlocs();
    List<Bloc> getBlocsSansFoyer();
}

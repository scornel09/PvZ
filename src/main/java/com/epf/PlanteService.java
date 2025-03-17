package com.epf;

import java.util.List;

public interface PlanteService {
    void createPlante(Plante plante);
    Plante getPlanteById(int id);
    void updatePlante(Plante plante);
    void deletePlante(int id);
    List<Plante> getAllPlantes();
}

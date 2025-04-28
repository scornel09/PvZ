package com.epf.service;

import java.util.List;

import com.epf.model.Plante;

public interface PlanteService {

    Plante createPlante(Plante plante);

    Plante getPlanteById(int id);

    Plante updatePlante(Plante plante);

    void deletePlante(int id);

    List<Plante> getAllPlantes();
}

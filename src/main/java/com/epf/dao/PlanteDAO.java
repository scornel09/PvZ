package com.epf.dao;

import java.util.List;

import com.epf.model.Plante;

public interface PlanteDAO {

    Plante create(Plante plante);

    Plante read(int id);

    Plante update(Plante plante);

    void delete(int id);

    List<Plante> findAll();
}

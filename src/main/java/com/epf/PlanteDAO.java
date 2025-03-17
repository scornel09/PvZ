package com.epf;

import java.util.List;

public interface PlanteDAO {
    void create(Plante plante);
    Plante read(int id);
    void update(Plante plante);
    void delete(int id);
    List<Plante> findAll();
}

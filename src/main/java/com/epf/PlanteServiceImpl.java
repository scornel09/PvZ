package com.epf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlanteServiceImpl implements PlanteService {

    private final PlanteDAO planteDAO;

    @Autowired
    public PlanteServiceImpl(PlanteDAO planteDAO) {
        this.planteDAO = planteDAO;
    }

    @Override
    public void createPlante(Plante plante) {
        planteDAO.create(plante);
    }

    @Override
    public Plante getPlanteById(int id) {
        return planteDAO.read(id);
    }

    @Override
    public void updatePlante(Plante plante) {
        planteDAO.update(plante);
    }

    @Override
    public void deletePlante(int id) {
        planteDAO.delete(id);
    }

    @Override
    public List<Plante> getAllPlantes() {
        return planteDAO.findAll();
    }
}


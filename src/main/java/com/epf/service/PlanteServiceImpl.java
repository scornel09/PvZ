package com.epf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.dao.PlanteDAO;
import com.epf.model.Plante;

@Service
public class PlanteServiceImpl implements PlanteService {

    private final PlanteDAO planteDAO;

    public PlanteServiceImpl(PlanteDAO planteDAO) {
        this.planteDAO = planteDAO;
    }

    public Plante createPlante(Plante plante) {
        return planteDAO.create(plante);
    }

    public Plante getPlanteById(int id) {
        return planteDAO.read(id);
    }

    public Plante updatePlante(Plante plante) {
        return planteDAO.update(plante);
    }

    public void deletePlante(int id) {
        planteDAO.delete(id);
    }

    public List<Plante> getAllPlantes() {
        return planteDAO.findAll();
    }
}

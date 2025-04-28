package com.epf;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.epf.config.PlanteDAOTestConfig;
import com.epf.dao.PlanteDAO;
import com.epf.model.Plante;
import com.epf.model.Plante.Effet;

@SpringJUnitConfig(PlanteDAOTestConfig.class)
public class PlanteDAOImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlanteDAO planteDAO;

    @BeforeEach
    void setUp() {
        jdbcTemplate.update("DELETE FROM plantes");
    }

    @Test
    void create_ShouldInsertPlante() {
        // Arrange
        Plante plante = new Plante("Tournesol", 100, 0.0f, 0, 50, 25.0f, Effet.NORMAL, "/images/plante/tournesol.png");

        // Act
        Plante createdPlante = planteDAO.create(plante);

        // Assert
        assertNotNull(createdPlante);
        assertEquals(plante.getNom(), createdPlante.getNom());
        assertEquals(plante.getPointDeVie(), createdPlante.getPointDeVie());
        assertEquals(plante.getAttaqueParSeconde(), createdPlante.getAttaqueParSeconde());
        assertEquals(plante.getCout(), createdPlante.getCout());
        assertEquals(plante.getSoleilParSeconde(), createdPlante.getSoleilParSeconde());
        assertEquals(plante.getEffet(), createdPlante.getEffet());
        assertEquals(plante.getCheminImage(), createdPlante.getCheminImage());
    }

    @Test
    void read_WhenPlanteExists_ShouldReturnPlante() {
        // Arrange
        Plante plante = new Plante("Pois", 100, 1.5f, 20, 100, 0.0f, Effet.NORMAL, "/images/plante/poistireur.png");
        Plante createdPlante = planteDAO.create(plante);

        // Act
        Plante foundPlante = planteDAO.read(createdPlante.getId());

        // Assert
        assertNotNull(foundPlante);
        assertEquals(createdPlante.getId(), foundPlante.getId());
        assertEquals(createdPlante.getNom(), foundPlante.getNom());
        assertEquals(createdPlante.getPointDeVie(), foundPlante.getPointDeVie());
        assertEquals(createdPlante.getAttaqueParSeconde(), foundPlante.getAttaqueParSeconde());
        assertEquals(createdPlante.getCout(), foundPlante.getCout());
        assertEquals(createdPlante.getSoleilParSeconde(), foundPlante.getSoleilParSeconde());
        assertEquals(createdPlante.getEffet(), foundPlante.getEffet());
        assertEquals(createdPlante.getCheminImage(), foundPlante.getCheminImage());
    }

    @Test
    void read_WhenPlanteDoesNotExist_ShouldReturnNull() {
        // Act
        Plante foundPlante = planteDAO.read(999);

        // Assert
        assertNull(foundPlante);
    }

    @Test
    void update_ShouldUpdatePlante() {
        // Arrange
        Plante plante = new Plante("Pois de Glace", 100, 1.5f, 20, 175, 0.0f, Effet.SLOW_LOW, "/images/plante/glacepois.png");
        Plante createdPlante = planteDAO.create(plante);
        createdPlante.setPointDeVie(150);
        createdPlante.setAttaqueParSeconde(1.8f);
        createdPlante.setDegatAttaque(25);
        createdPlante.setCout(200);
        createdPlante.setEffet(Effet.SLOW_MEDIUM);

        // Act
        Plante updatedPlante = planteDAO.update(createdPlante);

        // Assert
        assertNotNull(updatedPlante);
        assertEquals(createdPlante.getId(), updatedPlante.getId());
        assertEquals(150, updatedPlante.getPointDeVie());
        assertEquals(1.8f, updatedPlante.getAttaqueParSeconde());
        assertEquals(25, updatedPlante.getDegatAttaque());
        assertEquals(200, updatedPlante.getCout());
        assertEquals(Effet.SLOW_MEDIUM, updatedPlante.getEffet());
    }

    @Test
    void delete_ShouldRemovePlante() {
        // Arrange
        Plante plante = new Plante("Tournesol", 100, 0.0f, 0, 50, 25.0f, Effet.NORMAL, "/images/plante/tournesol.png");
        Plante createdPlante = planteDAO.create(plante);

        // Act
        planteDAO.delete(createdPlante.getId());

        // Assert
        Plante foundPlante = planteDAO.read(createdPlante.getId());
        assertNull(foundPlante);
    }

    @Test
    void findAll_ShouldReturnAllPlantes() {
        // Arrange
        Plante plante1 = new Plante("Tournesol", 100, 0.0f, 0, 50, 25.0f, Effet.NORMAL, "/images/plante/tournesol.png");
        Plante plante2 = new Plante("Pois", 100, 1.5f, 20, 100, 0.0f, Effet.NORMAL, "/images/plante/poistireur.png");
        planteDAO.create(plante1);
        planteDAO.create(plante2);

        // Act
        List<Plante> plantes = planteDAO.findAll();

        // Assert
        assertEquals(2, plantes.size());
    }
}

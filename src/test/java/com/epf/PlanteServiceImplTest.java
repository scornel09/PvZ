package com.epf;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epf.dao.PlanteDAO;
import com.epf.model.Plante;
import com.epf.service.PlanteServiceImpl;

@ExtendWith(MockitoExtension.class)
class PlanteServiceImplTest {

    @Mock
    private PlanteDAO planteDAO;

    private PlanteServiceImpl planteService;

    @BeforeEach
    void setUp() {
        planteService = new PlanteServiceImpl(planteDAO);
    }

    @Test
    void createPlante_ShouldCallDAOCreate() {
        // Arrange
        Plante plante = new Plante(0, "Test Plante", 100, 1.0f, 20, 100, 25.0f, Plante.Effet.NORMAL, "/images/plante/test.png", 2.0f);

        // Act
        planteService.createPlante(plante);

        // Assert
        verify(planteDAO).create(plante);
    }

    @Test
    void getPlanteById_ShouldReturnPlanteFromDAO() {
        // Arrange
        Plante expectedPlante = new Plante(1, "Test Plante", 100, 1.0f, 20, 100, 25.0f, Plante.Effet.NORMAL, "/images/plante/test.png", 2.0f);
        when(planteDAO.read(1)).thenReturn(expectedPlante);

        // Act
        Plante result = planteService.getPlanteById(1);

        // Assert
        assertNotNull(result);
        assertEquals(expectedPlante.getId(), result.getId());
        assertEquals(expectedPlante.getNom(), result.getNom());
        assertEquals(expectedPlante.getPointDeVie(), result.getPointDeVie());
        assertEquals(expectedPlante.getDegatAttaque(), result.getDegatAttaque());
        assertEquals(expectedPlante.getAttaqueParSeconde(), result.getAttaqueParSeconde());
        assertEquals(expectedPlante.getCout(), result.getCout());
        assertEquals(expectedPlante.getSoleilParSeconde(), result.getSoleilParSeconde());
        assertEquals(expectedPlante.getEffet(), result.getEffet());
        assertEquals(expectedPlante.getCheminImage(), result.getCheminImage());
    }

    @Test
    void updatePlante_ShouldCallDAOUpdate() {
        // Arrange
        Plante plante = new Plante(1, "Test Plante", 100, 1.0f, 20, 100, 25.0f, Plante.Effet.NORMAL, "/images/plante/test.png", 2.0f);

        // Act
        planteService.updatePlante(plante);

        // Assert
        verify(planteDAO).update(plante);
    }

    @Test
    void deletePlante_ShouldCallDAODelete() {
        // Act
        planteService.deletePlante(1);

        // Assert
        verify(planteDAO).delete(1);
    }

    @Test
    void getAllPlantes_ShouldReturnAllPlantesFromDAO() {
        // Arrange
        List<Plante> expectedPlantes = Arrays.asList(
                new Plante(1, "Plante 1", 100, 1.0f, 20, 100, 25.0f, Plante.Effet.NORMAL, "/images/plante/1.png", 2.0f),
                new Plante(2, "Plante 2", 150, 1.5f, 30, 150, 35.0f, Plante.Effet.SLOW_LOW, "/images/plante/2.png", 3.0f)
        );
        when(planteDAO.findAll()).thenReturn(expectedPlantes);

        // Act
        List<Plante> result = planteService.getAllPlantes();

        // Assert
        assertEquals(2, result.size());
        assertEquals(expectedPlantes.get(0).getNom(), result.get(0).getNom());
        assertEquals(expectedPlantes.get(1).getNom(), result.get(1).getNom());
    }
}

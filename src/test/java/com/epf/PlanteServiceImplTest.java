package com.epf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PlanteServiceImplTest {

    @Mock
    private PlanteDAO planteDAO;

    private PlanteServiceImpl planteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        planteService = new PlanteServiceImpl(planteDAO);
    }

    @Test
    void createPlante_ShouldCallDAOCreate() {
        // Arrange
        Plante plante = new Plante(1, "Test Plante", 100, 20, 1.0f, 100, 25.0f, SlowType.NORMAL);

        // Act
        planteService.createPlante(plante);

        // Assert
        verify(planteDAO).create(plante);
    }

    @Test
    void getPlanteById_ShouldReturnPlante() {
        // Arrange
        Plante expectedPlante = new Plante(1, "Test Plante", 100, 20, 1.0f, 100, 25.0f, SlowType.NORMAL);
        when(planteDAO.read(1)).thenReturn(expectedPlante);

        // Act
        Plante result = planteService.getPlanteById(1);

        // Assert
        assertEquals(expectedPlante, result);
        verify(planteDAO).read(1);
    }

    @Test
    void updatePlante_ShouldCallDAOUpdate() {
        // Arrange
        Plante plante = new Plante(1, "Test Plante", 100, 20, 1.0f, 100, 25.0f, SlowType.NORMAL);

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
    void getAllPlantes_ShouldReturnListOfPlantes() {
        // Arrange
        List<Plante> expectedPlantes = Arrays.asList(
                new Plante(1, "Plante 1", 100, 20, 1.0f, 100, 25.0f, SlowType.NORMAL),
                new Plante(2, "Plante 2", 150, 30, 1.5f, 150, 35.0f, SlowType.SLOW_LOW)
        );
        when(planteDAO.findAll()).thenReturn(expectedPlantes);

        // Act
        List<Plante> result = planteService.getAllPlantes();

        // Assert
        assertEquals(expectedPlantes, result);
        verify(planteDAO).findAll();
    }
}

package com.epf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PlanteControllerTest {

    @Mock
    private PlanteService planteService;

    private PlanteController planteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        planteController = new PlanteController(planteService);
    }

    @Test
    void createPlante_ShouldReturnCreatedStatus() {
        // Arrange
        PlanteDTO planteDTO = new PlanteDTO(1, "Test Plante", 100, 20, 1.0f, 100, 25.0f, SlowType.NORMAL);

        // Act
        ResponseEntity<Void> response = planteController.createPlante(planteDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(planteService).createPlante(any(Plante.class));
    }

    @Test
    void getPlante_WhenPlanteExists_ShouldReturnPlante() {
        // Arrange
        Plante plante = new Plante(1, "Test Plante", 100, 20, 1.0f, 100, 25.0f, SlowType.NORMAL);
        when(planteService.getPlanteById(1)).thenReturn(plante);

        // Act
        ResponseEntity<PlanteDTO> response = planteController.getPlante(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(plante.getId(), response.getBody().getId());
        assertEquals(plante.getName(), response.getBody().getName());
        assertEquals(plante.getHealth(), response.getBody().getHealth());
        assertEquals(plante.getDamage(), response.getBody().getDamage());
        assertEquals(plante.getAtkSpeed(), response.getBody().getAtkSpeed());
        assertEquals(plante.getCost(), response.getBody().getCost());
        assertEquals(plante.getSunPerSecond(), response.getBody().getSunPerSecond());
        assertEquals(plante.getSlowType(), response.getBody().getSlowType());
        verify(planteService).getPlanteById(1);
    }

    @Test
    void getPlante_WhenPlanteDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(planteService.getPlanteById(1)).thenReturn(null);

        // Act
        ResponseEntity<PlanteDTO> response = planteController.getPlante(1);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(planteService).getPlanteById(1);
    }

    @Test
    void updatePlante_ShouldReturnNoContent() {
        // Arrange
        PlanteDTO planteDTO = new PlanteDTO(1, "Test Plante", 100, 20, 1.0f, 100, 25.0f, SlowType.NORMAL);

        // Act
        ResponseEntity<Void> response = planteController.updatePlante(1, planteDTO);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(planteService).updatePlante(any(Plante.class));
    }

    @Test
    void deletePlante_ShouldReturnNoContent() {
        // Act
        ResponseEntity<Void> response = planteController.deletePlante(1);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(planteService).deletePlante(1);
    }

    @Test
    void getAllPlantes_ShouldReturnListOfPlantes() {
        // Arrange
        List<Plante> plantes = Arrays.asList(
                new Plante(1, "Plante 1", 100, 20, 1.0f, 100, 25.0f, SlowType.NORMAL),
                new Plante(2, "Plante 2", 150, 30, 1.5f, 150, 35.0f, SlowType.SLOW_LOW)
        );
        when(planteService.getAllPlantes()).thenReturn(plantes);

        // Act
        ResponseEntity<List<PlanteDTO>> response = planteController.getAllPlantes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(planteService).getAllPlantes();
    }
}

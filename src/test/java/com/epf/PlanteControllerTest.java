package com.epf;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.epf.controller.PlanteController;
import com.epf.dto.PlanteDTO;
import com.epf.model.Plante;
import com.epf.model.Plante.Effet;
import com.epf.service.PlanteService;

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
        PlanteDTO planteDTO = new PlanteDTO();
        planteDTO.setId(1);
        planteDTO.setNom("Test Plante");
        planteDTO.setPointDeVie(100);
        planteDTO.setTempsRecharge(1.0f);
        planteDTO.setAttaqueParSeconde(2.0f);
        planteDTO.setDegatAttaque(20);
        planteDTO.setCout(100);
        planteDTO.setSoleilParSeconde(1);
        planteDTO.setEffet("NORMAL");
        planteDTO.setCheminImage("chemin/image1.png");

        Plante plante = new Plante();
        plante.setId(1);
        plante.setNom("Test Plante");
        plante.setPointDeVie(100);
        plante.setTempsRecharge(1.0f);
        plante.setAttaqueParSeconde(2.0f);
        plante.setDegatAttaque(20);
        plante.setCout(100);
        plante.setSoleilParSeconde(1.0f);
        plante.setEffet(Effet.NORMAL);
        plante.setCheminImage("chemin/image1.png");

        when(planteService.createPlante(any(Plante.class))).thenReturn(plante);

        // Act
        ResponseEntity<PlanteDTO> response = planteController.createPlante(planteDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(planteService).createPlante(any(Plante.class));
    }

    @Test
    void getPlante_WhenPlanteExists_ShouldReturnPlante() {
        // Arrange
        Plante plante = new Plante();
        plante.setId(1);
        plante.setNom("Test Plante");
        plante.setPointDeVie(100);
        plante.setTempsRecharge(1.0f);
        plante.setAttaqueParSeconde(2.0f);
        plante.setDegatAttaque(20);
        plante.setCout(100);
        plante.setSoleilParSeconde(1.0f);
        plante.setEffet(Effet.NORMAL);
        plante.setCheminImage("chemin/image2.png");

        when(planteService.getPlanteById(1)).thenReturn(plante);

        // Act
        ResponseEntity<PlanteDTO> response = planteController.getPlanteById(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(plante.getId(), response.getBody().getId());
        assertEquals(plante.getNom(), response.getBody().getNom());
        assertEquals(plante.getPointDeVie(), response.getBody().getPointDeVie());
        assertEquals(plante.getDegatAttaque(), response.getBody().getDegatAttaque());
        assertEquals(plante.getAttaqueParSeconde(), response.getBody().getAttaqueParSeconde());
        assertEquals(plante.getCout(), response.getBody().getCout());
        assertEquals((int) Math.round(plante.getSoleilParSeconde()), response.getBody().getSoleilParSeconde());
        assertEquals(plante.getEffet().name(), response.getBody().getEffet());
        verify(planteService).getPlanteById(1);
    }

    @Test
    void getPlante_WhenPlanteDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(planteService.getPlanteById(1)).thenReturn(null);

        // Act
        ResponseEntity<PlanteDTO> response = planteController.getPlanteById(1);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(planteService).getPlanteById(1);
    }

    @Test
    void updatePlante_ShouldReturnNoContent() {
        // Arrange
        PlanteDTO planteDTO = new PlanteDTO();
        planteDTO.setId(1);
        planteDTO.setNom("Test Plante");
        planteDTO.setPointDeVie(100);
        planteDTO.setTempsRecharge(1.0f);
        planteDTO.setAttaqueParSeconde(2.0f);
        planteDTO.setDegatAttaque(20);
        planteDTO.setCout(100);
        planteDTO.setSoleilParSeconde(1);
        planteDTO.setEffet("NORMAL");
        planteDTO.setCheminImage("chemin/image3.png");

        Plante plante = new Plante();
        plante.setId(1);
        plante.setNom("Test Plante");
        plante.setPointDeVie(100);
        plante.setTempsRecharge(1.0f);
        plante.setAttaqueParSeconde(2.0f);
        plante.setDegatAttaque(20);
        plante.setCout(100);
        plante.setSoleilParSeconde(1.0f);
        plante.setEffet(Effet.NORMAL);
        plante.setCheminImage("chemin/image3.png");

        when(planteService.updatePlante(any(Plante.class))).thenReturn(plante);

        // Act
        ResponseEntity<PlanteDTO> response = planteController.updatePlante(1, planteDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(planteService).updatePlante(any(Plante.class));
    }

    @Test
    void deletePlante_ShouldReturnNoContent() {
        // Arrange
        // No setup needed for delete operation

        // Act
        ResponseEntity<Void> response = planteController.deletePlante(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(planteService).deletePlante(1);
    }

    @Test
    void getAllPlantes_ShouldReturnListOfPlantes() {
        // Arrange
        Plante plante = new Plante();
        plante.setId(1);
        plante.setNom("Test Plante");
        plante.setPointDeVie(100);
        plante.setTempsRecharge(1.0f);
        plante.setAttaqueParSeconde(2.0f);
        plante.setDegatAttaque(20);
        plante.setCout(100);
        plante.setSoleilParSeconde(1.0f);
        plante.setEffet(Effet.NORMAL);
        plante.setCheminImage("chemin/image4.png");

        List<Plante> plantes = Arrays.asList(plante);
        when(planteService.getAllPlantes()).thenReturn(plantes);

        // Act
        ResponseEntity<List<PlanteDTO>> response = planteController.getAllPlantes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        PlanteDTO retrievedPlante = response.getBody().get(0);
        assertEquals(plante.getId(), retrievedPlante.getId());
        assertEquals(plante.getNom(), retrievedPlante.getNom());
        assertEquals(plante.getPointDeVie(), retrievedPlante.getPointDeVie());
        assertEquals(plante.getDegatAttaque(), retrievedPlante.getDegatAttaque());
        assertEquals(plante.getAttaqueParSeconde(), retrievedPlante.getAttaqueParSeconde());
        assertEquals(plante.getCout(), retrievedPlante.getCout());
        assertEquals((int) Math.round(plante.getSoleilParSeconde()), retrievedPlante.getSoleilParSeconde());
        assertEquals(plante.getEffet().name(), retrievedPlante.getEffet());
    }
}

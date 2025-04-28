package com.epf;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.epf.controller.ZombieController;
import com.epf.dto.ZombieDTO;
import com.epf.model.Zombie;
import com.epf.service.ZombieService;

class ZombieControllerTest {

    @Mock
    private ZombieService zombieService;

    @InjectMocks
    private ZombieController zombieController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createZombie_ShouldReturnCreatedZombie() {
        // Arrange
        ZombieDTO zombieDTO = new ZombieDTO();
        zombieDTO.setNom("Test Zombie");
        zombieDTO.setPointDeVie(100);
        zombieDTO.setAttaqueParSeconde(new BigDecimal("1.0"));
        zombieDTO.setDegatAttaque(20);
        zombieDTO.setVitesseDeDeplacement(1.5f);
        zombieDTO.setCheminImage("test.png");
        zombieDTO.setIdMap(1);

        Zombie zombie = new Zombie();
        zombie.setId(1);
        zombie.setNom("Test Zombie");
        zombie.setPointDeVie(100);
        zombie.setAttaqueParSeconde(new BigDecimal("1.0"));
        zombie.setDegatAttaque(20);
        zombie.setVitesseDeDeplacement(1.5f);
        zombie.setCheminImage("test.png");
        zombie.setIdMap(1);

        when(zombieService.createZombie(any(Zombie.class))).thenReturn(zombie);

        // Act
        ResponseEntity<ZombieDTO> response = zombieController.createZombie(zombieDTO);

        // Assert
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(zombieDTO.getNom(), response.getBody().getNom());
        assertEquals(zombieDTO.getPointDeVie(), response.getBody().getPointDeVie());
        assertEquals(zombieDTO.getAttaqueParSeconde(), response.getBody().getAttaqueParSeconde());
        assertEquals(zombieDTO.getDegatAttaque(), response.getBody().getDegatAttaque());
        assertEquals(zombieDTO.getVitesseDeDeplacement(), response.getBody().getVitesseDeDeplacement());
        assertEquals(zombieDTO.getCheminImage(), response.getBody().getCheminImage());
        assertEquals(zombieDTO.getIdMap(), response.getBody().getIdMap());
    }

    @Test
    void getZombieById_WhenZombieExists_ShouldReturnZombie() {
        // Arrange
        Zombie zombie = new Zombie();
        zombie.setId(1);
        zombie.setNom("Test Zombie");
        zombie.setPointDeVie(100);
        zombie.setAttaqueParSeconde(new BigDecimal("1.0"));
        zombie.setDegatAttaque(20);
        zombie.setVitesseDeDeplacement(1.5f);
        zombie.setCheminImage("test.png");
        zombie.setIdMap(1);

        when(zombieService.getZombieById(1)).thenReturn(zombie);

        // Act
        ResponseEntity<ZombieDTO> response = zombieController.getZombieById(1);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(zombie.getNom(), response.getBody().getNom());
        assertEquals(zombie.getPointDeVie(), response.getBody().getPointDeVie());
        assertEquals(zombie.getAttaqueParSeconde(), response.getBody().getAttaqueParSeconde());
        assertEquals(zombie.getDegatAttaque(), response.getBody().getDegatAttaque());
        assertEquals(zombie.getVitesseDeDeplacement(), response.getBody().getVitesseDeDeplacement());
        assertEquals(zombie.getCheminImage(), response.getBody().getCheminImage());
        assertEquals(zombie.getIdMap(), response.getBody().getIdMap());
    }

    @Test
    void getZombieById_WhenZombieDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(zombieService.getZombieById(1)).thenReturn(null);

        // Act
        ResponseEntity<ZombieDTO> response = zombieController.getZombieById(1);

        // Assert
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void updateZombie_ShouldReturnUpdatedZombie() {
        // Arrange
        ZombieDTO zombieDTO = new ZombieDTO();
        zombieDTO.setNom("Updated Zombie");
        zombieDTO.setPointDeVie(150);
        zombieDTO.setAttaqueParSeconde(new BigDecimal("1.5"));
        zombieDTO.setDegatAttaque(30);
        zombieDTO.setVitesseDeDeplacement(2.0f);
        zombieDTO.setCheminImage("updated.png");
        zombieDTO.setIdMap(2);

        Zombie zombie = new Zombie();
        zombie.setId(1);
        zombie.setNom("Updated Zombie");
        zombie.setPointDeVie(150);
        zombie.setAttaqueParSeconde(new BigDecimal("1.5"));
        zombie.setDegatAttaque(30);
        zombie.setVitesseDeDeplacement(2.0f);
        zombie.setCheminImage("updated.png");
        zombie.setIdMap(2);

        when(zombieService.updateZombie(any(Zombie.class))).thenReturn(zombie);

        // Act
        ResponseEntity<ZombieDTO> response = zombieController.updateZombie(1, zombieDTO);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(zombieDTO.getNom(), response.getBody().getNom());
        assertEquals(zombieDTO.getPointDeVie(), response.getBody().getPointDeVie());
        assertEquals(zombieDTO.getAttaqueParSeconde(), response.getBody().getAttaqueParSeconde());
        assertEquals(zombieDTO.getDegatAttaque(), response.getBody().getDegatAttaque());
        assertEquals(zombieDTO.getVitesseDeDeplacement(), response.getBody().getVitesseDeDeplacement());
        assertEquals(zombieDTO.getCheminImage(), response.getBody().getCheminImage());
        assertEquals(zombieDTO.getIdMap(), response.getBody().getIdMap());
    }

    @Test
    void deleteZombie_ShouldReturnNoContent() {
        // Act
        ResponseEntity<Void> response = zombieController.deleteZombie(1);

        // Assert
        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(zombieService).deleteZombie(1);
    }

    @Test
    void getAllZombies_ShouldReturnListOfZombies() {
        // Arrange
        Zombie zombie1 = new Zombie();
        zombie1.setId(1);
        zombie1.setNom("Zombie 1");
        zombie1.setPointDeVie(100);
        zombie1.setAttaqueParSeconde(new BigDecimal("1.0"));
        zombie1.setDegatAttaque(20);
        zombie1.setVitesseDeDeplacement(1.5f);
        zombie1.setCheminImage("zombie1.png");
        zombie1.setIdMap(1);

        Zombie zombie2 = new Zombie();
        zombie2.setId(2);
        zombie2.setNom("Zombie 2");
        zombie2.setPointDeVie(150);
        zombie2.setAttaqueParSeconde(new BigDecimal("1.5"));
        zombie2.setDegatAttaque(30);
        zombie2.setVitesseDeDeplacement(2.0f);
        zombie2.setCheminImage("zombie2.png");
        zombie2.setIdMap(2);

        List<Zombie> zombies = Arrays.asList(zombie1, zombie2);
        when(zombieService.getAllZombies()).thenReturn(zombies);

        // Act
        ResponseEntity<List<ZombieDTO>> response = zombieController.getAllZombies();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void getZombiesByMapId_ShouldReturnListOfZombies() {
        // Arrange
        Zombie zombie1 = new Zombie();
        zombie1.setId(1);
        zombie1.setNom("Zombie 1");
        zombie1.setPointDeVie(100);
        zombie1.setAttaqueParSeconde(new BigDecimal("1.0"));
        zombie1.setDegatAttaque(20);
        zombie1.setVitesseDeDeplacement(1.5f);
        zombie1.setCheminImage("zombie1.png");
        zombie1.setIdMap(1);

        Zombie zombie2 = new Zombie();
        zombie2.setId(2);
        zombie2.setNom("Zombie 2");
        zombie2.setPointDeVie(150);
        zombie2.setAttaqueParSeconde(new BigDecimal("1.5"));
        zombie2.setDegatAttaque(30);
        zombie2.setVitesseDeDeplacement(2.0f);
        zombie2.setCheminImage("zombie2.png");
        zombie2.setIdMap(1);

        List<Zombie> zombies = Arrays.asList(zombie1, zombie2);
        when(zombieService.getZombiesByMapId(1)).thenReturn(zombies);

        // Act
        ResponseEntity<List<ZombieDTO>> response = zombieController.getZombiesByMapId(1);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }
}

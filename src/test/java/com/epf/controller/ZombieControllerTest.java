package com.epf.controller;

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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

        ResponseEntity<ZombieDTO> response = zombieController.createZombie(zombieDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(zombieDTO.getNom(), response.getBody().getNom());
        assertEquals(zombieDTO.getPointDeVie(), response.getBody().getPointDeVie());
        assertEquals(zombieDTO.getAttaqueParSeconde(), response.getBody().getAttaqueParSeconde());
        assertEquals(zombieDTO.getDegatAttaque(), response.getBody().getDegatAttaque());
        assertEquals(zombieDTO.getVitesseDeDeplacement(), response.getBody().getVitesseDeDeplacement());
        assertEquals(zombieDTO.getCheminImage(), response.getBody().getCheminImage());
        assertEquals(zombieDTO.getIdMap(), response.getBody().getIdMap());
        verify(zombieService).createZombie(any(Zombie.class));
    }

    @Test
    void getZombie_WhenZombieExists_ShouldReturnZombie() {
        Zombie zombie = new Zombie(1, "Test Zombie", 100, new BigDecimal("1.0"), 20, 1.5f, "test.png", 1);
        when(zombieService.getZombieById(1)).thenReturn(zombie);

        ResponseEntity<ZombieDTO> response = zombieController.getZombieById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(zombie.getNom(), response.getBody().getNom());
        assertEquals(zombie.getPointDeVie(), response.getBody().getPointDeVie());
        assertEquals(zombie.getAttaqueParSeconde(), response.getBody().getAttaqueParSeconde());
        assertEquals(zombie.getDegatAttaque(), response.getBody().getDegatAttaque());
        assertEquals(zombie.getVitesseDeDeplacement(), response.getBody().getVitesseDeDeplacement());
        assertEquals(zombie.getCheminImage(), response.getBody().getCheminImage());
        assertEquals(zombie.getIdMap(), response.getBody().getIdMap());
        verify(zombieService).getZombieById(1);
    }

    @Test
    void getZombie_WhenZombieDoesNotExist_ShouldReturnNotFound() {
        when(zombieService.getZombieById(1)).thenReturn(null);

        ResponseEntity<ZombieDTO> response = zombieController.getZombieById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(zombieService).getZombieById(1);
    }

    @Test
    void updateZombie_WhenZombieExists_ShouldReturnUpdatedZombie() {
        ZombieDTO zombieDTO = new ZombieDTO();
        zombieDTO.setNom("Updated Zombie");
        zombieDTO.setPointDeVie(150);
        zombieDTO.setAttaqueParSeconde(new BigDecimal("1.5"));
        zombieDTO.setDegatAttaque(30);
        zombieDTO.setVitesseDeDeplacement(2.0f);
        zombieDTO.setCheminImage("updated.png");
        zombieDTO.setIdMap(2);

        Zombie zombie = new Zombie(1, "Updated Zombie", 150, new BigDecimal("1.5"), 30, 2.0f, "updated.png", 2);
        when(zombieService.updateZombie(any(Zombie.class))).thenReturn(zombie);

        ResponseEntity<ZombieDTO> response = zombieController.updateZombie(1, zombieDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(zombieDTO.getNom(), response.getBody().getNom());
        assertEquals(zombieDTO.getPointDeVie(), response.getBody().getPointDeVie());
        assertEquals(zombieDTO.getAttaqueParSeconde(), response.getBody().getAttaqueParSeconde());
        assertEquals(zombieDTO.getDegatAttaque(), response.getBody().getDegatAttaque());
        assertEquals(zombieDTO.getVitesseDeDeplacement(), response.getBody().getVitesseDeDeplacement());
        assertEquals(zombieDTO.getCheminImage(), response.getBody().getCheminImage());
        assertEquals(zombieDTO.getIdMap(), response.getBody().getIdMap());
        verify(zombieService).updateZombie(any(Zombie.class));
    }

    @Test
    void updateZombie_WhenZombieDoesNotExist_ShouldReturnNotFound() {
        ZombieDTO zombieDTO = new ZombieDTO();
        zombieDTO.setNom("Updated Zombie");
        zombieDTO.setPointDeVie(150);
        zombieDTO.setAttaqueParSeconde(new BigDecimal("1.5"));
        zombieDTO.setDegatAttaque(30);
        zombieDTO.setVitesseDeDeplacement(2.0f);
        zombieDTO.setCheminImage("updated.png");
        zombieDTO.setIdMap(2);

        when(zombieService.updateZombie(any(Zombie.class))).thenReturn(null);

        ResponseEntity<ZombieDTO> response = zombieController.updateZombie(1, zombieDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(zombieService).updateZombie(any(Zombie.class));
    }

    @Test
    void deleteZombie_ShouldCallService() {
        doNothing().when(zombieService).deleteZombie(1);

        ResponseEntity<Void> response = zombieController.deleteZombie(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(zombieService).deleteZombie(1);
    }

    @Test
    void getAllZombies_ShouldReturnAllZombies() {
        List<Zombie> zombies = Arrays.asList(
                new Zombie(1, "Zombie 1", 100, new BigDecimal("1.0"), 20, 1.5f, "zombie1.png", 1),
                new Zombie(2, "Zombie 2", 150, new BigDecimal("1.5"), 30, 2.0f, "zombie2.png", 2)
        );
        when(zombieService.getAllZombies()).thenReturn(zombies);

        ResponseEntity<List<ZombieDTO>> response = zombieController.getAllZombies();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(zombieService).getAllZombies();
    }

    @Test
    void getZombiesByMapId_ShouldReturnListOfZombies() {
        Zombie zombie1 = new Zombie(1, "Zombie 1", 100, new BigDecimal("1.0"), 20, 1.5f, "zombie1.png", 1);
        Zombie zombie2 = new Zombie(2, "Zombie 2", 150, new BigDecimal("1.5"), 30, 2.0f, "zombie2.png", 2);
        when(zombieService.getZombiesByMapId(1)).thenReturn(Arrays.asList(zombie1, zombie2));

        ResponseEntity<List<ZombieDTO>> response = zombieController.getZombiesByMapId(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(zombieService).getZombiesByMapId(1);
    }
}

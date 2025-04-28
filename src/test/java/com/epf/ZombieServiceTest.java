package com.epf;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epf.dao.ZombieDAO;
import com.epf.model.Zombie;
import com.epf.service.ZombieService;

@ExtendWith(MockitoExtension.class)
class ZombieServiceTest {

    @Mock
    private ZombieDAO zombieDAO;

    @InjectMocks
    private ZombieService zombieService;

    private Zombie zombie;

    @BeforeEach
    void setUp() {
        zombie = new Zombie();
        zombie.setNom("Test Zombie");
        zombie.setPointDeVie(100);
        zombie.setAttaqueParSeconde(new BigDecimal("1.0"));
        zombie.setDegatAttaque(20);
        zombie.setVitesseDeDeplacement(1.5f);
        zombie.setCheminImage("test.png");
        zombie.setIdMap(1);
    }

    @Test
    void createZombie_ShouldReturnCreatedZombie() {
        // Arrange
        when(zombieDAO.create(zombie)).thenReturn(zombie);

        // Act
        Zombie createdZombie = zombieService.createZombie(zombie);

        // Assert
        assertNotNull(createdZombie);
        assertEquals(zombie.getNom(), createdZombie.getNom());
        assertEquals(zombie.getPointDeVie(), createdZombie.getPointDeVie());
        assertEquals(zombie.getAttaqueParSeconde(), createdZombie.getAttaqueParSeconde());
        assertEquals(zombie.getDegatAttaque(), createdZombie.getDegatAttaque());
        assertEquals(zombie.getVitesseDeDeplacement(), createdZombie.getVitesseDeDeplacement());
        assertEquals(zombie.getCheminImage(), createdZombie.getCheminImage());
        assertEquals(zombie.getIdMap(), createdZombie.getIdMap());
        verify(zombieDAO, times(1)).create(zombie);
    }

    @Test
    void getZombieById_WhenZombieExists_ShouldReturnZombie() {
        // Arrange
        when(zombieDAO.read(1)).thenReturn(zombie);

        // Act
        Zombie foundZombie = zombieService.getZombieById(1);

        // Assert
        assertNotNull(foundZombie);
        assertEquals(zombie.getNom(), foundZombie.getNom());
        assertEquals(zombie.getPointDeVie(), foundZombie.getPointDeVie());
        assertEquals(zombie.getAttaqueParSeconde(), foundZombie.getAttaqueParSeconde());
        assertEquals(zombie.getDegatAttaque(), foundZombie.getDegatAttaque());
        assertEquals(zombie.getVitesseDeDeplacement(), foundZombie.getVitesseDeDeplacement());
        assertEquals(zombie.getCheminImage(), foundZombie.getCheminImage());
        assertEquals(zombie.getIdMap(), foundZombie.getIdMap());
        verify(zombieDAO, times(1)).read(1);
    }

    @Test
    void getZombieById_WhenZombieDoesNotExist_ShouldReturnNull() {
        // Arrange
        when(zombieDAO.read(999)).thenReturn(null);

        // Act
        Zombie foundZombie = zombieService.getZombieById(999);

        // Assert
        assertNull(foundZombie);
        verify(zombieDAO, times(1)).read(999);
    }

    @Test
    void updateZombie_ShouldReturnUpdatedZombie() {
        // Arrange
        zombie.setNom("Updated Zombie");
        zombie.setPointDeVie(150);
        zombie.setAttaqueParSeconde(new BigDecimal("1.5"));
        zombie.setDegatAttaque(30);
        zombie.setVitesseDeDeplacement(2.0f);
        zombie.setCheminImage("updated.png");
        zombie.setIdMap(2);
        when(zombieDAO.update(zombie)).thenReturn(zombie);

        // Act
        Zombie updatedZombie = zombieService.updateZombie(zombie);

        // Assert
        assertNotNull(updatedZombie);
        assertEquals(zombie.getNom(), updatedZombie.getNom());
        assertEquals(zombie.getPointDeVie(), updatedZombie.getPointDeVie());
        assertEquals(zombie.getAttaqueParSeconde(), updatedZombie.getAttaqueParSeconde());
        assertEquals(zombie.getDegatAttaque(), updatedZombie.getDegatAttaque());
        assertEquals(zombie.getVitesseDeDeplacement(), updatedZombie.getVitesseDeDeplacement());
        assertEquals(zombie.getCheminImage(), updatedZombie.getCheminImage());
        assertEquals(zombie.getIdMap(), updatedZombie.getIdMap());
        verify(zombieDAO, times(1)).update(zombie);
    }

    @Test
    void deleteZombie_ShouldCallDeleteOnDAO() {
        // Act
        zombieService.deleteZombie(1);

        // Assert
        verify(zombieDAO, times(1)).delete(1);
    }

    @Test
    void getAllZombies_ShouldReturnListOfZombies() {
        // Arrange
        Zombie zombie1 = new Zombie();
        zombie1.setNom("Zombie 1");
        zombie1.setPointDeVie(100);
        zombie1.setAttaqueParSeconde(new BigDecimal("1.0"));
        zombie1.setDegatAttaque(20);
        zombie1.setVitesseDeDeplacement(1.5f);
        zombie1.setCheminImage("zombie1.png");
        zombie1.setIdMap(1);

        Zombie zombie2 = new Zombie();
        zombie2.setNom("Zombie 2");
        zombie2.setPointDeVie(150);
        zombie2.setAttaqueParSeconde(new BigDecimal("1.5"));
        zombie2.setDegatAttaque(30);
        zombie2.setVitesseDeDeplacement(2.0f);
        zombie2.setCheminImage("zombie2.png");
        zombie2.setIdMap(2);

        List<Zombie> zombies = Arrays.asList(zombie1, zombie2);
        when(zombieDAO.findAll()).thenReturn(zombies);

        // Act
        List<Zombie> foundZombies = zombieService.getAllZombies();

        // Assert
        assertNotNull(foundZombies);
        assertEquals(2, foundZombies.size());
        verify(zombieDAO, times(1)).findAll();
    }

    @Test
    void getZombiesByMapId_ShouldReturnListOfZombies() {
        // Arrange
        Zombie zombie1 = new Zombie();
        zombie1.setNom("Zombie 1");
        zombie1.setPointDeVie(100);
        zombie1.setAttaqueParSeconde(new BigDecimal("1.0"));
        zombie1.setDegatAttaque(20);
        zombie1.setVitesseDeDeplacement(1.5f);
        zombie1.setCheminImage("zombie1.png");
        zombie1.setIdMap(1);

        Zombie zombie2 = new Zombie();
        zombie2.setNom("Zombie 2");
        zombie2.setPointDeVie(150);
        zombie2.setAttaqueParSeconde(new BigDecimal("1.5"));
        zombie2.setDegatAttaque(30);
        zombie2.setVitesseDeDeplacement(2.0f);
        zombie2.setCheminImage("zombie2.png");
        zombie2.setIdMap(1);

        List<Zombie> zombies = Arrays.asList(zombie1, zombie2);
        when(zombieDAO.findByMapId(1)).thenReturn(zombies);

        // Act
        List<Zombie> foundZombies = zombieService.getZombiesByMapId(1);

        // Assert
        assertNotNull(foundZombies);
        assertEquals(2, foundZombies.size());
        verify(zombieDAO, times(1)).findByMapId(1);
    }
}

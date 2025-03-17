package com.epf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ZombieServiceImplTest {

    @Mock
    private ZombieDAO zombieDAO;

    private ZombieServiceImpl zombieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        zombieService = new ZombieServiceImpl(zombieDAO);
    }

    @Test
    void createZombie_ShouldCallDAOCreate() {
        // Arrange
        Zombie zombie = new Zombie(1, "Test Zombie", 100, 1.0f, 1.0f, 1);

        // Act
        zombieService.createZombie(zombie);

        // Assert
        verify(zombieDAO).create(zombie);
    }

    @Test
    void getZombieById_ShouldReturnZombie() {
        // Arrange
        Zombie expectedZombie = new Zombie(1, "Test Zombie", 100, 1.0f, 1.0f, 1);
        when(zombieDAO.read(1)).thenReturn(expectedZombie);

        // Act
        Zombie result = zombieService.getZombieById(1);

        // Assert
        assertEquals(expectedZombie, result);
        verify(zombieDAO).read(1);
    }

    @Test
    void updateZombie_ShouldCallDAOUpdate() {
        // Arrange
        Zombie zombie = new Zombie(1, "Test Zombie", 100, 1.0f, 1.0f, 1);

        // Act
        zombieService.updateZombie(zombie);

        // Assert
        verify(zombieDAO).update(zombie);
    }

    @Test
    void deleteZombie_ShouldCallDAODelete() {
        // Act
        zombieService.deleteZombie(1);

        // Assert
        verify(zombieDAO).delete(1);
    }

    @Test
    void getAllZombies_ShouldReturnListOfZombies() {
        // Arrange
        List<Zombie> expectedZombies = Arrays.asList(
                new Zombie(1, "Zombie 1", 100, 1.0f, 1.0f, 1),
                new Zombie(2, "Zombie 2", 100, 1.0f, 1.0f, 1)
        );
        when(zombieDAO.findAll()).thenReturn(expectedZombies);

        // Act
        List<Zombie> result = zombieService.getAllZombies();

        // Assert
        assertEquals(expectedZombies, result);
        verify(zombieDAO).findAll();
    }

    @Test
    void getZombiesByMapId_ShouldReturnListOfZombies() {
        // Arrange
        List<Zombie> expectedZombies = Arrays.asList(
                new Zombie(1, "Zombie 1", 100, 1.0f, 1.0f, 1),
                new Zombie(2, "Zombie 2", 100, 1.0f, 1.0f, 1)
        );
        when(zombieDAO.findByMapId(1)).thenReturn(expectedZombies);

        // Act
        List<Zombie> result = zombieService.getZombiesByMapId(1);

        // Assert
        assertEquals(expectedZombies, result);
        verify(zombieDAO).findByMapId(1);
    }
}

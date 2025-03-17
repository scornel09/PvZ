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

class ZombieControllerTest {

    @Mock
    private ZombieService zombieService;

    private ZombieController zombieController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        zombieController = new ZombieController(zombieService);
    }

    @Test
    void createZombie_ShouldReturnCreatedStatus() {
        // Arrange
        ZombieDTO zombieDTO = new ZombieDTO(1, "Test Zombie", 100, 1.0f, 1.0f, 1);

        // Act
        ResponseEntity<Void> response = zombieController.createZombie(zombieDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(zombieService).createZombie(any(Zombie.class));
    }

    @Test
    void getZombie_WhenZombieExists_ShouldReturnZombie() {
        // Arrange
        Zombie zombie = new Zombie(1, "Test Zombie", 100, 1.0f, 1.0f, 1);
        when(zombieService.getZombieById(1)).thenReturn(zombie);

        // Act
        ResponseEntity<ZombieDTO> response = zombieController.getZombie(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(zombie.getId(), response.getBody().getId());
        assertEquals(zombie.getName(), response.getBody().getName());
        verify(zombieService).getZombieById(1);
    }

    @Test
    void getZombie_WhenZombieDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(zombieService.getZombieById(1)).thenReturn(null);

        // Act
        ResponseEntity<ZombieDTO> response = zombieController.getZombie(1);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(zombieService).getZombieById(1);
    }

    @Test
    void updateZombie_ShouldReturnNoContent() {
        // Arrange
        ZombieDTO zombieDTO = new ZombieDTO(1, "Test Zombie", 100, 1.0f, 1.0f, 1);

        // Act
        ResponseEntity<Void> response = zombieController.updateZombie(1, zombieDTO);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(zombieService).updateZombie(any(Zombie.class));
    }

    @Test
    void deleteZombie_ShouldReturnNoContent() {
        // Act
        ResponseEntity<Void> response = zombieController.deleteZombie(1);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(zombieService).deleteZombie(1);
    }

    @Test
    void getAllZombies_ShouldReturnListOfZombies() {
        // Arrange
        List<Zombie> zombies = Arrays.asList(
                new Zombie(1, "Zombie 1", 100, 1.0f, 1.0f, 1),
                new Zombie(2, "Zombie 2", 100, 1.0f, 1.0f, 1)
        );
        when(zombieService.getAllZombies()).thenReturn(zombies);

        // Act
        ResponseEntity<List<ZombieDTO>> response = zombieController.getAllZombies();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(zombieService).getAllZombies();
    }

    @Test
    void getZombiesByMap_ShouldReturnListOfZombies() {
        // Arrange
        List<Zombie> zombies = Arrays.asList(
                new Zombie(1, "Zombie 1", 100, 1.0f, 1.0f, 1),
                new Zombie(2, "Zombie 2", 100, 1.0f, 1.0f, 1)
        );
        when(zombieService.getZombiesByMapId(1)).thenReturn(zombies);

        // Act
        ResponseEntity<List<ZombieDTO>> response = zombieController.getZombiesByMap(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(zombieService).getZombiesByMapId(1);
    }
}

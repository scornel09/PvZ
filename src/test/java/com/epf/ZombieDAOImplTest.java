package com.epf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:test-config.xml")
class ZombieDAOImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ZombieDAOImpl zombieDAO;

    @BeforeEach
    void setUp() {
        zombieDAO = new ZombieDAOImpl(jdbcTemplate);
    }

    @Test
    void create_ShouldInsertZombie() {
        // Arrange
        Zombie zombie = new Zombie(1, "Test Zombie", 100, 1.0f, 1.0f, 1);

        // Act
        zombieDAO.create(zombie);

        // Assert
        Zombie createdZombie = zombieDAO.read(1);
        assertNotNull(createdZombie);
        assertEquals(zombie.getName(), createdZombie.getName());
        assertEquals(zombie.getHealth(), createdZombie.getHealth());
        assertEquals(zombie.getAtkSpeed(), createdZombie.getAtkSpeed());
        assertEquals(zombie.getMvtSpeed(), createdZombie.getMvtSpeed());
        assertEquals(zombie.getMapId(), createdZombie.getMapId());
    }

    @Test
    void read_WhenZombieExists_ShouldReturnZombie() {
        // Arrange
        Zombie zombie = new Zombie(1, "Test Zombie", 100, 1.0f, 1.0f, 1);
        zombieDAO.create(zombie);

        // Act
        Zombie result = zombieDAO.read(1);

        // Assert
        assertNotNull(result);
        assertEquals(zombie.getName(), result.getName());
        assertEquals(zombie.getHealth(), result.getHealth());
        assertEquals(zombie.getAtkSpeed(), result.getAtkSpeed());
        assertEquals(zombie.getMvtSpeed(), result.getMvtSpeed());
        assertEquals(zombie.getMapId(), result.getMapId());
    }

    @Test
    void read_WhenZombieDoesNotExist_ShouldReturnNull() {
        // Act
        Zombie result = zombieDAO.read(999);

        // Assert
        assertNull(result);
    }

    @Test
    void update_ShouldUpdateZombie() {
        // Arrange
        Zombie zombie = new Zombie(1, "Test Zombie", 100, 1.0f, 1.0f, 1);
        zombieDAO.create(zombie);
        Zombie updatedZombie = new Zombie(1, "Updated Zombie", 200, 2.0f, 2.0f, 2);

        // Act
        zombieDAO.update(updatedZombie);

        // Assert
        Zombie result = zombieDAO.read(1);
        assertNotNull(result);
        assertEquals(updatedZombie.getName(), result.getName());
        assertEquals(updatedZombie.getHealth(), result.getHealth());
        assertEquals(updatedZombie.getAtkSpeed(), result.getAtkSpeed());
        assertEquals(updatedZombie.getMvtSpeed(), result.getMvtSpeed());
        assertEquals(updatedZombie.getMapId(), result.getMapId());
    }

    @Test
    void delete_ShouldRemoveZombie() {
        // Arrange
        Zombie zombie = new Zombie(1, "Test Zombie", 100, 1.0f, 1.0f, 1);
        zombieDAO.create(zombie);

        // Act
        zombieDAO.delete(1);

        // Assert
        Zombie result = zombieDAO.read(1);
        assertNull(result);
    }

    @Test
    void findAll_ShouldReturnAllZombies() {
        // Arrange
        Zombie zombie1 = new Zombie(1, "Zombie 1", 100, 1.0f, 1.0f, 1);
        Zombie zombie2 = new Zombie(2, "Zombie 2", 200, 2.0f, 2.0f, 2);
        zombieDAO.create(zombie1);
        zombieDAO.create(zombie2);

        // Act
        List<Zombie> zombies = zombieDAO.findAll();

        // Assert
        assertEquals(2, zombies.size());
        assertTrue(zombies.stream().anyMatch(z -> z.getName().equals("Zombie 1")));
        assertTrue(zombies.stream().anyMatch(z -> z.getName().equals("Zombie 2")));
    }

    @Test
    void findByMapId_ShouldReturnZombiesForMap() {
        // Arrange
        Zombie zombie1 = new Zombie(1, "Zombie 1", 100, 1.0f, 1.0f, 1);
        Zombie zombie2 = new Zombie(2, "Zombie 2", 200, 2.0f, 2.0f, 2);
        zombieDAO.create(zombie1);
        zombieDAO.create(zombie2);

        // Act
        List<Zombie> zombies = zombieDAO.findByMapId(1);

        // Assert
        assertEquals(1, zombies.size());
        assertEquals("Zombie 1", zombies.get(0).getName());
    }
}

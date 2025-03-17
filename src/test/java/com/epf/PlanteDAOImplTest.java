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
class PlanteDAOImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PlanteDAOImpl planteDAO;

    @BeforeEach
    void setUp() {
        planteDAO = new PlanteDAOImpl(jdbcTemplate);
    }

    @Test
    void create_ShouldInsertPlante() {
        // Arrange
        Plante plante = new Plante(1, "Test Plante", 100, 20, 1.0f, 100, 25.0f, SlowType.NORMAL);

        // Act
        planteDAO.create(plante);

        // Assert
        Plante createdPlante = planteDAO.read(1);
        assertNotNull(createdPlante);
        assertEquals(plante.getName(), createdPlante.getName());
        assertEquals(plante.getHealth(), createdPlante.getHealth());
        assertEquals(plante.getDamage(), createdPlante.getDamage());
        assertEquals(plante.getAtkSpeed(), createdPlante.getAtkSpeed());
        assertEquals(plante.getCost(), createdPlante.getCost());
        assertEquals(plante.getSunPerSecond(), createdPlante.getSunPerSecond());
        assertEquals(plante.getSlowType(), createdPlante.getSlowType());
    }

    @Test
    void read_WhenPlanteExists_ShouldReturnPlante() {
        // Arrange
        Plante plante = new Plante(1, "Test Plante", 100, 20, 1.0f, 100, 25.0f, SlowType.NORMAL);
        planteDAO.create(plante);

        // Act
        Plante result = planteDAO.read(1);

        // Assert
        assertNotNull(result);
        assertEquals(plante.getName(), result.getName());
        assertEquals(plante.getHealth(), result.getHealth());
        assertEquals(plante.getDamage(), result.getDamage());
        assertEquals(plante.getAtkSpeed(), result.getAtkSpeed());
        assertEquals(plante.getCost(), result.getCost());
        assertEquals(plante.getSunPerSecond(), result.getSunPerSecond());
        assertEquals(plante.getSlowType(), result.getSlowType());
    }

    @Test
    void read_WhenPlanteDoesNotExist_ShouldReturnNull() {
        // Act
        Plante result = planteDAO.read(999);

        // Assert
        assertNull(result);
    }

    @Test
    void update_ShouldUpdatePlante() {
        // Arrange
        Plante plante = new Plante(1, "Test Plante", 100, 20, 1.0f, 100, 25.0f, SlowType.NORMAL);
        planteDAO.create(plante);
        Plante updatedPlante = new Plante(1, "Updated Plante", 150, 30, 1.5f, 150, 35.0f, SlowType.SLOW_LOW);

        // Act
        planteDAO.update(updatedPlante);

        // Assert
        Plante result = planteDAO.read(1);
        assertNotNull(result);
        assertEquals(updatedPlante.getName(), result.getName());
        assertEquals(updatedPlante.getHealth(), result.getHealth());
        assertEquals(updatedPlante.getDamage(), result.getDamage());
        assertEquals(updatedPlante.getAtkSpeed(), result.getAtkSpeed());
        assertEquals(updatedPlante.getCost(), result.getCost());
        assertEquals(updatedPlante.getSunPerSecond(), result.getSunPerSecond());
        assertEquals(updatedPlante.getSlowType(), result.getSlowType());
    }

    @Test
    void delete_ShouldRemovePlante() {
        // Arrange
        Plante plante = new Plante(1, "Test Plante", 100, 20, 1.0f, 100, 25.0f, SlowType.NORMAL);
        planteDAO.create(plante);

        // Act
        planteDAO.delete(1);

        // Assert
        Plante result = planteDAO.read(1);
        assertNull(result);
    }

    @Test
    void findAll_ShouldReturnAllPlantes() {
        // Arrange
        Plante plante1 = new Plante(1, "Plante 1", 100, 20, 1.0f, 100, 25.0f, SlowType.NORMAL);
        Plante plante2 = new Plante(2, "Plante 2", 150, 30, 1.5f, 150, 35.0f, SlowType.SLOW_LOW);
        planteDAO.create(plante1);
        planteDAO.create(plante2);

        // Act
        List<Plante> plantes = planteDAO.findAll();

        // Assert
        assertEquals(2, plantes.size());
        assertTrue(plantes.stream().anyMatch(p -> p.getName().equals("Plante 1")));
        assertTrue(plantes.stream().anyMatch(p -> p.getName().equals("Plante 2")));
    }
}

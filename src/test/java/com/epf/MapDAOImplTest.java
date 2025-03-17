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
class MapDAOImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private MapDAOImpl mapDAO;

    @BeforeEach
    void setUp() {
        mapDAO = new MapDAOImpl(jdbcTemplate);
    }

    @Test
    void create_ShouldInsertMap() {
        // Arrange
        Map map = new Map(1, 5, 9);

        // Act
        mapDAO.create(map);

        // Assert
        Map createdMap = mapDAO.read(1);
        assertNotNull(createdMap);
        assertEquals(map.getLigne(), createdMap.getLigne());
        assertEquals(map.getColonne(), createdMap.getColonne());
    }

    @Test
    void read_WhenMapExists_ShouldReturnMap() {
        // Arrange
        Map map = new Map(1, 5, 9);
        mapDAO.create(map);

        // Act
        Map result = mapDAO.read(1);

        // Assert
        assertNotNull(result);
        assertEquals(map.getLigne(), result.getLigne());
        assertEquals(map.getColonne(), result.getColonne());
    }

    @Test
    void read_WhenMapDoesNotExist_ShouldReturnNull() {
        // Act
        Map result = mapDAO.read(999);

        // Assert
        assertNull(result);
    }

    @Test
    void update_ShouldUpdateMap() {
        // Arrange
        Map map = new Map(1, 5, 9);
        mapDAO.create(map);
        Map updatedMap = new Map(1, 6, 10);

        // Act
        mapDAO.update(updatedMap);

        // Assert
        Map result = mapDAO.read(1);
        assertNotNull(result);
        assertEquals(updatedMap.getLigne(), result.getLigne());
        assertEquals(updatedMap.getColonne(), result.getColonne());
    }

    @Test
    void delete_ShouldRemoveMap() {
        // Arrange
        Map map = new Map(1, 5, 9);
        mapDAO.create(map);

        // Act
        mapDAO.delete(1);

        // Assert
        Map result = mapDAO.read(1);
        assertNull(result);
    }

    @Test
    void findAll_ShouldReturnAllMaps() {
        // Arrange
        Map map1 = new Map(1, 5, 9);
        Map map2 = new Map(2, 6, 10);
        mapDAO.create(map1);
        mapDAO.create(map2);

        // Act
        List<Map> maps = mapDAO.findAll();

        // Assert
        assertEquals(2, maps.size());
        assertTrue(maps.stream().anyMatch(m -> m.getLigne() == 5));
        assertTrue(maps.stream().anyMatch(m -> m.getLigne() == 6));
    }
}

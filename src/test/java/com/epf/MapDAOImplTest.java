package com.epf;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.epf.config.MapDAOTestConfig;
import com.epf.dao.MapDAO;
import com.epf.model.Map;

@SpringJUnitConfig(MapDAOTestConfig.class)
class MapDAOImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MapDAO mapDAO;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DELETE FROM zombies");
        jdbcTemplate.execute("DELETE FROM maps");
    }

    @Test
    void create_ShouldInsertMap() {
        // Arrange
        Map map = new Map(5, 9, "/images/map/gazon.png");

        // Act
        mapDAO.create(map);

        // Assert
        List<Map> maps = mapDAO.findAll();
        assertEquals(1, maps.size());
        Map createdMap = maps.get(0);
        assertEquals(5, createdMap.getLigne());
        assertEquals(9, createdMap.getColonne());
        assertEquals("/images/map/gazon.png", createdMap.getCheminImage());
    }

    @Test
    void read_WhenMapExists_ShouldReturnMap() {
        // Arrange
        Map map = new Map(5, 9, "/images/map/gazon.png");
        mapDAO.create(map);

        // Act
        Map foundMap = mapDAO.read(map.getId());

        // Assert
        assertNotNull(foundMap);
        assertEquals(5, foundMap.getLigne());
        assertEquals(9, foundMap.getColonne());
        assertEquals("/images/map/gazon.png", foundMap.getCheminImage());
    }

    @Test
    void update_ShouldUpdateMap() {
        // Arrange
        Map map = new Map(5, 9, "/images/map/gazon.png");
        mapDAO.create(map);
        map.setLigne(6);
        map.setColonne(10);
        map.setCheminImage("/images/map/desert.png");

        // Act
        Map updatedMap = mapDAO.update(map);

        // Assert
        assertNotNull(updatedMap);
        assertEquals(6, updatedMap.getLigne());
        assertEquals(10, updatedMap.getColonne());
        assertEquals("/images/map/desert.png", updatedMap.getCheminImage());
    }

    @Test
    void delete_ShouldRemoveMap() {
        // Arrange
        Map map = new Map(5, 9, "/images/map/gazon.png");
        mapDAO.create(map);

        // Act
        mapDAO.delete(map.getId());

        // Assert
        Map foundMap = mapDAO.read(map.getId());
        assertNull(foundMap);
    }

    @Test
    void findAll_ShouldReturnAllMaps() {
        // Arrange
        Map map1 = new Map(5, 9, "/images/map/gazon.png");
        Map map2 = new Map(6, 10, "/images/map/desert.png");
        mapDAO.create(map1);
        mapDAO.create(map2);

        // Act
        List<Map> maps = mapDAO.findAll();

        // Assert
        assertEquals(2, maps.size());
        Map foundMap1 = maps.get(0);
        Map foundMap2 = maps.get(1);
        assertEquals(5, foundMap1.getLigne());
        assertEquals(9, foundMap1.getColonne());
        assertEquals("/images/map/gazon.png", foundMap1.getCheminImage());
        assertEquals(6, foundMap2.getLigne());
        assertEquals(10, foundMap2.getColonne());
        assertEquals("/images/map/desert.png", foundMap2.getCheminImage());
    }
}

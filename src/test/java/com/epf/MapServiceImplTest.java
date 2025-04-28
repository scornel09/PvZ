package com.epf;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epf.dao.MapDAO;
import com.epf.model.Map;
import com.epf.service.MapServiceImpl;

@ExtendWith(MockitoExtension.class)
class MapServiceImplTest {

    @Mock
    private MapDAO mapDAO;

    private MapServiceImpl mapService;

    @BeforeEach
    void setUp() {
        mapService = new MapServiceImpl(mapDAO);
    }

    @Test
    void createMap_ShouldCallDAOCreate() {
        // Arrange
        Map map = new Map(5, 6, "chemin/image1.png");

        // Act
        mapService.createMap(map);

        // Assert
        verify(mapDAO).create(map);
    }

    @Test
    void getMapById_ShouldReturnMapFromDAO() {
        // Arrange
        Map expectedMap = new Map(5, 6, "chemin/image2.png");
        when(mapDAO.read(1)).thenReturn(expectedMap);

        // Act
        Map result = mapService.getMapById(1);

        // Assert
        assertNotNull(result);
        assertEquals(expectedMap.getId(), result.getId());
        assertEquals(expectedMap.getLigne(), result.getLigne());
        assertEquals(expectedMap.getColonne(), result.getColonne());
    }

    @Test
    void updateMap_ShouldCallDAOUpdate() {
        // Arrange
        Map map = new Map(5, 6, "chemin/image3.png");

        // Act
        mapService.updateMap(map);

        // Assert
        verify(mapDAO).update(map);
    }

    @Test
    void deleteMap_ShouldCallDAODelete() {
        // Act
        mapService.deleteMap(1);

        // Assert
        verify(mapDAO).delete(1);
    }

    @Test
    void getAllMaps_ShouldReturnAllMapsFromDAO() {
        // Arrange
        List<Map> expectedMaps = Arrays.asList(
                new Map(5, 6, "chemin/image4.png"),
                new Map(6, 7, "chemin/image5.png")
        );
        when(mapDAO.findAll()).thenReturn(expectedMaps);

        // Act
        List<Map> result = mapService.getAllMaps();

        // Assert
        assertEquals(2, result.size());
        assertEquals(expectedMaps.get(0).getLigne(), result.get(0).getLigne());
        assertEquals(expectedMaps.get(1).getLigne(), result.get(1).getLigne());
    }
}

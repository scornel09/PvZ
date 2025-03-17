package com.epf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MapServiceImplTest {

    @Mock
    private MapDAO mapDAO;

    private MapServiceImpl mapService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mapService = new MapServiceImpl(mapDAO);
    }

    @Test
    void createMap_ShouldCallDAOCreate() {
        // Arrange
        Map map = new Map(1, 5, 9);

        // Act
        mapService.createMap(map);

        // Assert
        verify(mapDAO).create(map);
    }

    @Test
    void getMapById_ShouldReturnMap() {
        // Arrange
        Map expectedMap = new Map(1, 5, 9);
        when(mapDAO.read(1)).thenReturn(expectedMap);

        // Act
        Map result = mapService.getMapById(1);

        // Assert
        assertEquals(expectedMap, result);
        verify(mapDAO).read(1);
    }

    @Test
    void updateMap_ShouldCallDAOUpdate() {
        // Arrange
        Map map = new Map(1, 5, 9);

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
    void getAllMaps_ShouldReturnListOfMaps() {
        // Arrange
        List<Map> expectedMaps = Arrays.asList(
                new Map(1, 5, 9),
                new Map(2, 6, 10)
        );
        when(mapDAO.findAll()).thenReturn(expectedMaps);

        // Act
        List<Map> result = mapService.getAllMaps();

        // Assert
        assertEquals(expectedMaps, result);
        verify(mapDAO).findAll();
    }
}

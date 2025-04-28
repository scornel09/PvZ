package com.epf;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.epf.controller.MapController;
import com.epf.dto.MapDTO;
import com.epf.model.Map;
import com.epf.service.MapService;

class MapControllerTest {

    @Mock
    private MapService mapService;

    private MapController mapController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mapController = new MapController(mapService);
    }

    @Test
    void createMap_ShouldReturnCreatedStatus() {
        // Arrange
        MapDTO mapDTO = new MapDTO(1, 5, 9, "chemin/image1.png");
        Map map = new Map(5, 9, "chemin/image1.png");
        map.setId(1);
        when(mapService.createMap(any(Map.class))).thenReturn(map);

        // Act
        ResponseEntity<MapDTO> response = mapController.createMap(mapDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(mapService).createMap(any(Map.class));
    }

    @Test
    void getMap_WhenMapExists_ShouldReturnMap() {
        // Arrange
        Map map = new Map(5, 9, "chemin/image2.png");
        when(mapService.getMapById(1)).thenReturn(map);

        // Act
        ResponseEntity<MapDTO> response = mapController.getMap(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(map.getId(), response.getBody().getId());
        assertEquals(map.getLigne(), response.getBody().getLigne());
        assertEquals(map.getColonne(), response.getBody().getColonne());
        verify(mapService).getMapById(1);
    }

    @Test
    void getMap_WhenMapDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(mapService.getMapById(1)).thenReturn(null);

        // Act
        ResponseEntity<MapDTO> response = mapController.getMap(1);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(mapService).getMapById(1);
    }

    @Test
    void updateMap_ShouldReturnNoContent() {
        // Arrange
        MapDTO mapDTO = new MapDTO(1, 5, 9, "chemin/image3.png");
        Map map = new Map(5, 9, "chemin/image3.png");
        map.setId(1);
        when(mapService.updateMap(any(Map.class))).thenReturn(map);

        // Act
        ResponseEntity<MapDTO> response = mapController.updateMap(1, mapDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(mapService).updateMap(any(Map.class));
    }

    @Test
    void deleteMap_ShouldReturnNoContent() {
        // Act
        ResponseEntity<Void> response = mapController.deleteMap(1);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(mapService).deleteMap(1);
    }

    @Test
    void getAllMaps_ShouldReturnListOfMaps() {
        // Arrange
        List<Map> maps = Arrays.asList(
                new Map(5, 9, "chemin/image4.png"),
                new Map(6, 10, "chemin/image5.png")
        );
        when(mapService.getAllMaps()).thenReturn(maps);

        // Act
        ResponseEntity<List<MapDTO>> response = mapController.getAllMaps();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(mapService).getAllMaps();
    }
}

package com.epf.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epf.dto.MapDTO;
import com.epf.model.Map;
import com.epf.service.MapService;

@RestController
@RequestMapping("/maps")
public class MapController {

    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @PostMapping
    public ResponseEntity<MapDTO> createMap(@RequestBody MapDTO mapDTO) {
        Map map = new Map(mapDTO.getLigne(), mapDTO.getColonne(), mapDTO.getCheminImage());
        Map createdMap = mapService.createMap(map);
        MapDTO createdMapDTO = convertToDTO(createdMap);
        return ResponseEntity.created(URI.create("/maps/" + createdMapDTO.getId())).body(createdMapDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MapDTO> getMap(@PathVariable int id) {
        Map map = mapService.getMapById(id);
        if (map != null) {
            return new ResponseEntity<>(convertToDTO(map), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MapDTO> updateMap(@PathVariable int id, @RequestBody MapDTO mapDTO) {
        Map map = new Map(mapDTO.getLigne(), mapDTO.getColonne(), mapDTO.getCheminImage());
        map.setId(id);
        Map updatedMap = mapService.updateMap(map);
        return new ResponseEntity<>(convertToDTO(updatedMap), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMap(@PathVariable int id) {
        mapService.deleteMap(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<MapDTO>> getAllMaps() {
        List<Map> maps = mapService.getAllMaps();
        List<MapDTO> mapDTOs = maps.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(mapDTOs, HttpStatus.OK);
    }

    private MapDTO convertToDTO(Map map) {
        return new MapDTO(map.getId(), map.getLigne(), map.getColonne(), map.getCheminImage());
    }
}

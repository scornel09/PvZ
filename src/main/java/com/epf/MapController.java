package com.epf;
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

@RestController
@RequestMapping("/api/maps")
public class MapController {

    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @PostMapping
    public ResponseEntity<Void> createMap(@RequestBody MapDTO mapDTO) {
        Map map = new Map(mapDTO.getId(), mapDTO.getLigne(), mapDTO.getColonne());
        mapService.createMap(map);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MapDTO> getMap(@PathVariable int id) {
        Map map = mapService.getMapById(id);
        if (map == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MapDTO mapDTO = new MapDTO(map.getId(), map.getLigne(), map.getColonne());
        return new ResponseEntity<>(mapDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMap(@PathVariable int id, @RequestBody MapDTO mapDTO) {
        Map map = new Map(id, mapDTO.getLigne(), mapDTO.getColonne());
        mapService.updateMap(map);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
                .map(m -> new MapDTO(m.getId(), m.getLigne(), m.getColonne()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(mapDTOs, HttpStatus.OK);
    }
}

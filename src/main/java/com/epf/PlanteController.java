package com.epf;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/plantes")
public class PlanteController {

    private final PlanteService planteService;

    @Autowired
    public PlanteController(PlanteService planteService) {
        this.planteService = planteService;
    }

    @PostMapping
    public ResponseEntity<Void> createPlante(@RequestBody PlanteDTO planteDTO) {
        Plante plante = new Plante(planteDTO.getId(), planteDTO.getName(), planteDTO.getHealth(), planteDTO.getDamage(), planteDTO.getAtkSpeed(), planteDTO.getCost(), planteDTO.getSunPerSecond(), planteDTO.getSlowType());
        planteService.createPlante(plante);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanteDTO> getPlante(@PathVariable int id) {
        Plante plante = planteService.getPlanteById(id);
        if (plante == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new PlanteDTO(plante.getId(), plante.getName(), plante.getHealth(), plante.getDamage(), plante.getAtkSpeed(), plante.getCost(), plante.getSunPerSecond(), plante.getSlowType()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePlante(@PathVariable int id, @RequestBody PlanteDTO planteDTO) {
        Plante plante = new Plante(id, planteDTO.getName(), planteDTO.getHealth(), planteDTO.getDamage(), planteDTO.getAtkSpeed(), planteDTO.getCost(), planteDTO.getSunPerSecond(), planteDTO.getSlowType());
        planteService.updatePlante(plante);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlante(@PathVariable int id) {
        planteService.deletePlante(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PlanteDTO>> getAllPlantes() {
        List<PlanteDTO> plantes = planteService.getAllPlantes().stream()
                .map(p -> new PlanteDTO(p.getId(), p.getName(), p.getHealth(), p.getDamage(), p.getAtkSpeed(), p.getCost(), p.getSunPerSecond(), p.getSlowType()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(plantes);
    }
}

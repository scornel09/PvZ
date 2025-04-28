package com.epf.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epf.dto.PlanteDTO;
import com.epf.model.Plante;
import com.epf.model.Plante.Effet;
import com.epf.service.PlanteService;

@RestController
@RequestMapping("/plantes")
@CrossOrigin(origins = "http://localhost:5173")
public class PlanteController {

    private static final Logger logger = LoggerFactory.getLogger(PlanteController.class);
    private final PlanteService planteService;

    public PlanteController(PlanteService planteService) {
        this.planteService = planteService;
    }

    @PostMapping
    public ResponseEntity<PlanteDTO> createPlante(@RequestBody PlanteDTO planteDTO) {
        try {
            System.out.println("=== Début de la création d'une plante ===");
            System.out.println("Données reçues : " + planteDTO);

            logger.info("Tentative de création d'une plante avec les données: {}", planteDTO);

            // Log des valeurs reçues
            System.out.println("Détails de la plante reçue:");
            System.out.println("ID: " + planteDTO.getId());
            System.out.println("Nom: " + planteDTO.getNom());
            System.out.println("Point de vie: " + planteDTO.getPointDeVie());
            System.out.println("Temps recharge: " + planteDTO.getTempsRecharge());
            System.out.println("Attaque par seconde: " + planteDTO.getAttaqueParSeconde());
            System.out.println("Dégât attaque: " + planteDTO.getDegatAttaque());
            System.out.println("Coût: " + planteDTO.getCout());
            System.out.println("Soleil par seconde: " + planteDTO.getSoleilParSeconde());
            System.out.println("Effet: " + planteDTO.getEffet());
            System.out.println("Chemin image: " + planteDTO.getCheminImage());

            Plante plante = convertToEntity(planteDTO);
            System.out.println("Plante convertie: " + plante);

            Plante createdPlante = planteService.createPlante(plante);
            System.out.println("Plante créée: " + createdPlante);

            PlanteDTO createdPlanteDTO = convertToDTO(createdPlante);
            System.out.println("Plante DTO créée: " + createdPlanteDTO);

            System.out.println("=== Fin de la création d'une plante ===");
            return ResponseEntity.created(URI.create("/plantes/" + createdPlanteDTO.getId())).body(createdPlanteDTO);
        } catch (Exception e) {
            System.out.println("ERREUR lors de la création de la plante: " + e.getMessage());
            e.printStackTrace();
            logger.error("Erreur lors de la création de la plante", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanteDTO> getPlanteById(@PathVariable int id) {
        try {
            System.out.println("Tentative de récupération de la plante avec l'ID: " + id);
            Plante plante = planteService.getPlanteById(id);
            if (plante != null) {
                PlanteDTO dto = convertToDTO(plante);
                System.out.println("Plante trouvée: " + dto);
                return ResponseEntity.ok(dto);
            }
            System.out.println("Plante non trouvée avec l'ID: " + id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println("ERREUR lors de la récupération de la plante: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanteDTO> updatePlante(@PathVariable int id, @RequestBody PlanteDTO planteDTO) {
        try {
            System.out.println("Tentative de mise à jour de la plante avec l'ID: " + id);
            Plante plante = convertToEntity(planteDTO);
            plante.setId(id);
            Plante updatedPlante = planteService.updatePlante(plante);
            PlanteDTO updatedDTO = convertToDTO(updatedPlante);
            System.out.println("Plante mise à jour: " + updatedDTO);
            return ResponseEntity.ok(updatedDTO);
        } catch (Exception e) {
            System.out.println("ERREUR lors de la mise à jour de la plante: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlante(@PathVariable int id) {
        try {
            System.out.println("Tentative de suppression de la plante avec l'ID: " + id);
            planteService.deletePlante(id);
            System.out.println("Plante supprimée avec succès");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("ERREUR lors de la suppression de la plante: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PlanteDTO>> getAllPlantes() {
        try {
            System.out.println("Tentative de récupération de toutes les plantes");
            List<Plante> plantes = planteService.getAllPlantes();
            List<PlanteDTO> planteDTOs = plantes.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            System.out.println(planteDTOs.size() + " plantes récupérées");
            return ResponseEntity.ok(planteDTOs);
        } catch (Exception e) {
            System.out.println("ERREUR lors de la récupération des plantes: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    private Plante convertToEntity(PlanteDTO dto) {
        System.out.println("Conversion DTO vers Entity");
        Plante plante = new Plante();
        plante.setId(dto.getId());
        plante.setNom(dto.getNom());
        plante.setPointDeVie(dto.getPointDeVie());
        plante.setTempsRecharge(dto.getTempsRecharge());
        plante.setAttaqueParSeconde(dto.getAttaqueParSeconde());
        plante.setDegatAttaque(dto.getDegatAttaque());
        plante.setCout(dto.getCout());
        plante.setSoleilParSeconde(dto.getSoleilParSeconde());
        try {
            plante.setEffet(dto.getEffet() != null ? Effet.valueOf(dto.getEffet().toUpperCase()) : Effet.NORMAL);
        } catch (IllegalArgumentException e) {
            System.out.println("Effet invalide: " + dto.getEffet() + ", utilisation de NORMAL par défaut");
            plante.setEffet(Effet.NORMAL);
        }
        plante.setCheminImage(dto.getCheminImage());
        System.out.println("Entity convertie: " + plante);
        return plante;
    }

    private PlanteDTO convertToDTO(Plante plante) {
        System.out.println("Conversion Entity vers DTO");
        PlanteDTO dto = new PlanteDTO();
        dto.setId(plante.getId());
        dto.setNom(plante.getNom());
        dto.setPointDeVie(plante.getPointDeVie());
        dto.setTempsRecharge(plante.getTempsRecharge());
        dto.setAttaqueParSeconde(plante.getAttaqueParSeconde());
        dto.setDegatAttaque(plante.getDegatAttaque());
        dto.setCout(plante.getCout());
        dto.setSoleilParSeconde((int) Math.round(plante.getSoleilParSeconde()));
        dto.setEffet(plante.getEffet() != null ? plante.getEffet().name() : Effet.NORMAL.name());
        dto.setCheminImage(plante.getCheminImage());
        System.out.println("DTO converti: " + dto);
        return dto;
    }
}

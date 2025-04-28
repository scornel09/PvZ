package com.epf.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.epf.dto.ZombieDTO;
import com.epf.model.Zombie;
import com.epf.service.ZombieService;

@RestController
@RequestMapping("/zombies")
@CrossOrigin(origins = "http://localhost:5173")
public class ZombieController {

    @Autowired
    private ZombieService zombieService;

    @PostMapping
    public ResponseEntity<ZombieDTO> createZombie(@RequestBody ZombieDTO zombieDTO) {
        try {
            System.out.println("Received zombie data: " + zombieDTO);
            Zombie zombie = convertToEntity(zombieDTO);
            System.out.println("Converted to entity: " + zombie);
            Zombie createdZombie = zombieService.createZombie(zombie);
            System.out.println("Created zombie: " + createdZombie);
            return ResponseEntity.ok(convertToDTO(createdZombie));
        } catch (Exception e) {
            System.out.println("Error creating zombie: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZombieDTO> getZombieById(@PathVariable int id) {
        try {
            System.out.println("Getting zombie with id: " + id);
            Zombie zombie = zombieService.getZombieById(id);
            System.out.println("Found zombie: " + zombie);

            if (zombie == null) {
                System.out.println("No zombie found with id: " + id);
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(convertToDTO(zombie));
        } catch (Exception e) {
            System.out.println("Error getting zombie: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZombieDTO> updateZombie(@PathVariable int id, @RequestBody ZombieDTO zombieDTO) {
        System.out.println("Updating zombie with id: " + id);
        System.out.println("Received data: " + zombieDTO);
        Zombie zombie = convertToEntity(zombieDTO);
        zombie.setId(id);
        System.out.println("Converted to entity: " + zombie);
        Zombie updatedZombie = zombieService.updateZombie(zombie);
        System.out.println("Updated zombie: " + updatedZombie);
        return ResponseEntity.ok(convertToDTO(updatedZombie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZombie(@PathVariable int id) {
        System.out.println("Deleting zombie with id: " + id);
        zombieService.deleteZombie(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ZombieDTO>> getAllZombies() {
        System.out.println("Getting all zombies");
        List<Zombie> zombies = zombieService.getAllZombies();
        System.out.println("Found " + zombies.size() + " zombies");
        return ResponseEntity.ok(zombies.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/map/{mapId}")
    public ResponseEntity<List<ZombieDTO>> getZombiesByMapId(@PathVariable int mapId) {
        try {
            List<Zombie> zombies = zombieService.getZombiesByMapId(mapId);
            List<ZombieDTO> zombieDTOs = zombies.stream()
                    .map(this::convertToDTO)
                    .toList();
            return new ResponseEntity<>(zombieDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Zombie convertToEntity(ZombieDTO dto) {
        Zombie zombie = new Zombie();
        zombie.setId(dto.getId());
        zombie.setNom(dto.getNom());
        zombie.setPointDeVie(dto.getPointDeVie());
        zombie.setAttaqueParSeconde(dto.getAttaqueParSeconde());
        zombie.setDegatAttaque(dto.getDegatAttaque());
        zombie.setVitesseDeDeplacement(dto.getVitesseDeDeplacement());
        zombie.setCheminImage(dto.getCheminImage());
        zombie.setIdMap(dto.getIdMap());
        return zombie;
    }

    private ZombieDTO convertToDTO(Zombie zombie) {
        ZombieDTO dto = new ZombieDTO();
        dto.setId(zombie.getId());
        dto.setNom(zombie.getNom());
        dto.setPointDeVie(zombie.getPointDeVie());
        dto.setAttaqueParSeconde(zombie.getAttaqueParSeconde());
        dto.setDegatAttaque(zombie.getDegatAttaque());
        dto.setVitesseDeDeplacement(zombie.getVitesseDeDeplacement());
        dto.setCheminImage(zombie.getCheminImage());
        dto.setIdMap(zombie.getIdMap());
        return dto;
    }
}

package com.epf;

import java.util.List;
import java.util.stream.Collectors;

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
@RequestMapping("/api/zombies")
public class ZombieController {

    private final ZombieService zombieService;

    public ZombieController(ZombieService zombieService) {
        this.zombieService = zombieService;
    }

    @PostMapping
    public ResponseEntity<Void> createZombie(@RequestBody ZombieDTO zombieDTO) {
        Zombie zombie = new Zombie(zombieDTO.getId(), zombieDTO.getName(), zombieDTO.getHealth(), zombieDTO.getAtkSpeed(), zombieDTO.getMvtSpeed(), zombieDTO.getMapId());
        zombieService.createZombie(zombie);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZombieDTO> getZombie(@PathVariable int id) {
        Zombie zombie = zombieService.getZombieById(id);
        return zombie != null
                ? ResponseEntity.ok(new ZombieDTO(zombie.getId(), zombie.getName(), zombie.getHealth(), zombie.getAtkSpeed(), zombie.getMvtSpeed(), zombie.getMapId()))
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateZombie(@PathVariable int id, @RequestBody ZombieDTO zombieDTO) {
        Zombie zombie = new Zombie(id, zombieDTO.getName(), zombieDTO.getHealth(), zombieDTO.getAtkSpeed(), zombieDTO.getMvtSpeed(), zombieDTO.getMapId());
        zombieService.updateZombie(zombie);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZombie(@PathVariable int id) {
        zombieService.deleteZombie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ZombieDTO>> getAllZombies() {
        List<ZombieDTO> zombieDTOs = zombieService.getAllZombies().stream()
                .map(z -> new ZombieDTO(z.getId(), z.getName(), z.getHealth(), z.getAtkSpeed(), z.getMvtSpeed(), z.getMapId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(zombieDTOs);
    }

    @GetMapping("/map/{mapId}")
    public ResponseEntity<List<ZombieDTO>> getZombiesByMap(@PathVariable int mapId) {
        List<ZombieDTO> zombieDTOs = zombieService.getZombiesByMapId(mapId).stream()
                .map(z -> new ZombieDTO(z.getId(), z.getName(), z.getHealth(), z.getAtkSpeed(), z.getMvtSpeed(), z.getMapId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(zombieDTOs);
    }
}

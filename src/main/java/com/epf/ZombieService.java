package com.epf;

import java.util.List;

public interface ZombieService {
    void createZombie(Zombie zombie);
    Zombie getZombieById(int id);
    void updateZombie(Zombie zombie);
    void deleteZombie(int id);
    List<Zombie> getAllZombies();
    List<Zombie> getZombiesByMapId(int mapId);
}

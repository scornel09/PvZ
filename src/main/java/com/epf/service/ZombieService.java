package com.epf.service;

import java.util.List;

import com.epf.model.Zombie;

public interface ZombieService {

    Zombie createZombie(Zombie zombie);

    Zombie getZombieById(int id);

    Zombie updateZombie(Zombie zombie);

    void deleteZombie(int id);

    List<Zombie> getAllZombies();

    List<Zombie> getZombiesByMapId(int mapId);
}

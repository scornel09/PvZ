package com.epf;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ZombieServiceImpl implements ZombieService {

    private final ZombieDAO zombieDAO;

    public ZombieServiceImpl(ZombieDAO zombieDAO) {
        this.zombieDAO = zombieDAO;
    }

    @Override
    public void createZombie(Zombie zombie) {
        zombieDAO.create(zombie);
    }

    @Override
    public Zombie getZombieById(int id) {
        return zombieDAO.read(id);
    }

    @Override
    public void updateZombie(Zombie zombie) {
        zombieDAO.update(zombie);
    }

    @Override
    public void deleteZombie(int id) {
        zombieDAO.delete(id);
    }

    @Override
    public List<Zombie> getAllZombies() {
        return zombieDAO.findAll();
    }

    @Override
    public List<Zombie> getZombiesByMapId(int mapId) {
        return zombieDAO.findByMapId(mapId);
    }
}

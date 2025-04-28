package com.epf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.dao.ZombieDAO;
import com.epf.model.Zombie;

@Service
public class ZombieServiceImpl implements ZombieService {

    private final ZombieDAO zombieDAO;

    @Autowired
    public ZombieServiceImpl(ZombieDAO zombieDAO) {
        this.zombieDAO = zombieDAO;
    }

    @Override
    public Zombie createZombie(Zombie zombie) {
        return zombieDAO.create(zombie);
    }

    @Override
    public Zombie getZombieById(int id) {
        return zombieDAO.read(id);
    }

    @Override
    public Zombie updateZombie(Zombie zombie) {
        return zombieDAO.update(zombie);
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

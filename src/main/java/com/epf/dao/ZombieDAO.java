package com.epf.dao;

import java.util.List;

import com.epf.model.Zombie;

public interface ZombieDAO {

    // Créer
    Zombie create(Zombie zombie);

    // Lire
    Zombie read(int id);

    // Mettre à jour
    Zombie update(Zombie zombie);

    // Supprimer
    void delete(int id);

    // Récupérer tous les zombies
    List<Zombie> findAll();

    // Récupérer les zombies correspondant à une carte donnée
    List<Zombie> findByMapId(int mapId);

}

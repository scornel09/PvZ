package com.epf;

import java.util.List;

public interface ZombieDAO {
    // Créer
    void create(Zombie zombie);

    // Lire
    Zombie read(int id);

    // Mettre à jour
    void update(Zombie zombie);

    // Supprimer
    void delete(int id);

    // Récupérer tous les zombies
    List<Zombie> findAll();

    // Récupérer les zombies correspondant à une carte donnée
    List<Zombie> findByMapId(int mapId);

}


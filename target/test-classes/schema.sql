DROP TABLE IF EXISTS zombies;
DROP TABLE IF EXISTS plante;
DROP TABLE IF EXISTS maps;

CREATE TABLE zombies (
    id_zombie INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) NOT NULL,
    points_de_vie INT NOT NULL,
    vitesse INT NOT NULL,
    degats INT NOT NULL,
    chemin_image VARCHAR(255) NOT NULL,
    attaque_par_seconde DECIMAL(5,2) NOT NULL,
    id_map INT NOT NULL
);

CREATE TABLE plante (
    id_plante INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) NOT NULL,
    point_de_vie INT NOT NULL,
    temps_recharge FLOAT NOT NULL,
    degat_attaque INT NOT NULL,
    cout INT NOT NULL,
    soleil_par_seconde DECIMAL(5,2) NOT NULL,
    effet VARCHAR(255) NOT NULL,
    chemin_image VARCHAR(255) NOT NULL,
    attaque_par_seconde DECIMAL(5,2) NOT NULL
);

CREATE TABLE maps (
    id_map INT PRIMARY KEY AUTO_INCREMENT,
    ligne INT NOT NULL,
    colonne INT NOT NULL,
    chemin_image VARCHAR(255) NOT NULL
); 
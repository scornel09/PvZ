CREATE TABLE zombies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    health INT NOT NULL,
    atkSpeed FLOAT NOT NULL,
    mvtSpeed FLOAT NOT NULL,
    map_id INT NOT NULL
);

CREATE TABLE plantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    health INT NOT NULL,
    damage INT NOT NULL,
    atkSpeed FLOAT NOT NULL,
    cost INT NOT NULL,
    sunPerSecond FLOAT NOT NULL,
    slowType VARCHAR(50) NOT NULL
);

CREATE TABLE maps (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ligne INT NOT NULL,
    colonne INT NOT NULL
); 
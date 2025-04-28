-- Insert test maps
INSERT INTO maps (ligne, colonne, chemin_image) VALUES (5, 9, '/images/map/map1.png');
INSERT INTO maps (ligne, colonne, chemin_image) VALUES (5, 9, '/images/map/map2.png');
INSERT INTO maps (ligne, colonne, chemin_image) VALUES (5, 9, '/images/map/map3.png');

-- Insert test plantes
INSERT INTO plantes (nom, points_de_vie, degats, cout_soleil, temps_recharge, type, chemin_image) VALUES
('Tournesol', 100, 0, 50, 5, 'SOLEIL', '/images/plante/tournesol.png'),
('Pois', 100, 20, 100, 1.5, 'ATTAQUE', '/images/plante/pois.png'),
('Noix', 400, 0, 50, 10, 'DEFENSE', '/images/plante/noix.png');

-- Insert test zombies
INSERT INTO zombies (nom, points_de_vie, vitesse, degats, chemin_image) VALUES
('Zombie Standard', 100, 1.0, 20, '/images/zombie/zombie_standard.png'),
('Zombie Rapide', 80, 2.0, 15, '/images/zombie/zombie_rapide.png'),
('Zombie Tank', 200, 0.5, 30, '/images/zombie/zombie_tank.png'); 
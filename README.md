# Projet Plants vs Zombies

Ce projet est une implémentation du jeu Plants vs Zombies en utilisant une architecture client-serveur.

## Architecture

Le projet est divisé en deux parties principales :
- Backend : API REST en Java Spring Boot
- Frontend : Application React TypeScript

## Prérequis

- Java 17 ou supérieur
- Node.js 16 ou supérieur
- MySQL 8.0 ou supérieur
- Maven 3.8 ou supérieur

## Installation

### Backend

1. Cloner le repository
2. Configurer la base de données MySQL :
   - Créer une base de données nommée `pvz`
   - Les paramètres de connexion sont dans `src/main/resources/application.properties`
   - La base de données sera créée automatiquement si elle n'existe pas

3. Compiler et exécuter le backend :
```bash
cd projet_pvz
mvn clean install
mvn spring-boot:run
```

Le serveur backend démarrera sur `http://localhost:8080`

### Frontend

1. Installer les dépendances :
```bash
cd zip_PVZ_V1
npm install
```

2. Démarrer l'application :
```bash
npm run dev
```

L'application frontend sera disponible sur `http://localhost:5173`

## Structure du Projet

### Backend

```
src/main/java/com/epf/
├── config/           # Configuration Spring
├── controller/       # Contrôleurs REST
├── dao/             # Accès aux données
├── dto/             # Objets de transfert de données
├── model/           # Entités JPA
└── service/         # Logique métier
```

### Frontend

```
zip_PVZ_V1/
├── src/
│   ├── domains/     # Types et logique métier
│   ├── jeu_pvz/     # Composants du jeu
│   ├── pages/       # Pages de l'application
│   └── utils/       # Utilitaires
```

## Fonctionnalités

### Gestion des Plantes
- Création, lecture, mise à jour et suppression des plantes
- Différents types de plantes avec des effets variés
- Gestion des points de vie, dégâts et coûts

### Gestion des Zombies
- Création, lecture, mise à jour et suppression des zombies
- Différents types de zombies avec des caractéristiques variées
- Gestion des points de vie, dégâts et vitesse

### Gestion des Maps
- Création, lecture, mise à jour et suppression des maps
- Configuration des dimensions (lignes/colonnes)
- Gestion des images de fond

### Jeu
- Placement des plantes sur la map
- Déplacement des zombies
- Système de combat
- Gestion des ressources (soleil)

## Gestion des Images (ne fonctionne pas)

Les images sont stockées dans le dossier `src/main/webapp/images/` avec la structure suivante :
```
images/
├── plante/     # Images des plantes
├── zombie/     # Images des zombies
└── map/        # Images des maps
```

Les images sont servies via l'API à l'URL : `http://localhost:8080/api/images/{type}/{nom_image}`

## API REST

### Plantes
- `GET /api/plantes` : Liste toutes les plantes
- `GET /api/plantes/{id}` : Récupère une plante par son ID
- `POST /api/plantes` : Crée une nouvelle plante
- `PUT /api/plantes/{id}` : Met à jour une plante
- `DELETE /api/plantes/{id}` : Supprime une plante

### Zombies
- `GET /api/zombies` : Liste tous les zombies
- `GET /api/zombies/{id}` : Récupère un zombie par son ID
- `POST /api/zombies` : Crée un nouveau zombie
- `PUT /api/zombies/{id}` : Met à jour un zombie
- `DELETE /api/zombies/{id}` : Supprime un zombie

### Maps
- `GET /api/maps` : Liste toutes les maps
- `GET /api/maps/{id}` : Récupère une map par son ID
- `POST /api/maps` : Crée une nouvelle map
- `PUT /api/maps/{id}` : Met à jour une map
- `DELETE /api/maps/{id}` : Supprime une map

## Contribution

1. Fork le projet
2. Crée une branche pour votre fonctionnalité (`git checkout -b feature/AmazingFeature`)
3. Commit vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Push vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

## Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails. 
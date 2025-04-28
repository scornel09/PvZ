import { axiosClient } from "../axiosClient.ts";
import { Zombie } from "./zombies.types.ts";

const API_URL = '/zombies';

// Fonction pour transformer les données du backend vers le frontend
function transformBackendToFrontend(data: any): Zombie {
  return {
    id_zombie: data.id,
    nom: data.nom,
    point_de_vie: data.pointsDeVie,
    attaque_par_seconde: data.attaqueParSeconde,
    degat_attaque: data.degats,
    vitesse_de_deplacement: data.vitesse,
    chemin_image: data.cheminImage,
    id_map: data.mapId
  };
}

// Fonction pour transformer les données du frontend vers le backend
function transformFrontendToBackend(data: Partial<Zombie>): any {
  return {
    nom: data.nom,
    pointsDeVie: data.point_de_vie,
    attaqueParSeconde: data.attaque_par_seconde,
    degats: data.degat_attaque,
    vitesse: data.vitesse_de_deplacement,
    cheminImage: data.chemin_image,
    mapId: data.id_map
  };
}

export async function getZombies(): Promise<Zombie[]> {
  const response = await axiosClient.get(API_URL);
  return response.data.map(transformBackendToFrontend);
}

export async function getZombie(id_zombie: number): Promise<Zombie> {
  const response = await axiosClient.get(`${API_URL}/${id_zombie}`);
  return transformBackendToFrontend(response.data);
}

export async function createZombie(zombieData: Omit<Zombie, 'id_zombie'>): Promise<Zombie> {
  const backendData = transformFrontendToBackend(zombieData);
  const response = await axiosClient.post(API_URL, backendData);
  return transformBackendToFrontend(response.data);
}

export async function updateZombie(id_zombie: number, zombieData: Partial<Zombie>): Promise<Zombie> {
  const backendData = transformFrontendToBackend(zombieData);
  const response = await axiosClient.put(`${API_URL}/${id_zombie}`, backendData);
  return transformBackendToFrontend(response.data);
}

export async function deleteZombie(id_zombie: number): Promise<void> {
  await axiosClient.delete(`${API_URL}/${id_zombie}`);
}
import { axiosClient } from '../axiosClient.ts'; 
import { GameMap } from "./maps.types.ts";

const API_URL = '/maps';

// Fonction pour transformer les données du backend vers le frontend
function transformBackendToFrontend(data: any): GameMap {
  return {
    id_map: data.id,
    ligne: data.ligne,
    colonne: data.colonne,
    chemin_image: data.cheminImage
  };
}

// Fonction pour transformer les données du frontend vers le backend
function transformFrontendToBackend(data: Partial<GameMap>): any {
  return {
    ligne: data.ligne,
    colonne: data.colonne,
    cheminImage: data.chemin_image
  };
}

export async function getMaps(): Promise<GameMap[]> {
  const response = await axiosClient.get(API_URL);
  return response.data.map(transformBackendToFrontend);
}

export async function getMap(id_map: number): Promise<GameMap> {
  const response = await axiosClient.get(`${API_URL}/${id_map}`);
  return transformBackendToFrontend(response.data);
}

export async function createMap(mapData: Omit<GameMap, 'id_map'>): Promise<GameMap> {
  const backendData = transformFrontendToBackend(mapData);
  const response = await axiosClient.post(API_URL, backendData);
  return transformBackendToFrontend(response.data);
}

export async function updateMap(id_map: number, mapData: Partial<GameMap>): Promise<GameMap> {
  const backendData = transformFrontendToBackend(mapData);
  const response = await axiosClient.put(`${API_URL}/${id_map}`, backendData);
  return transformBackendToFrontend(response.data);
}

export async function deleteMap(id_map: number): Promise<void> {
  await axiosClient.delete(`${API_URL}/${id_map}`);
}
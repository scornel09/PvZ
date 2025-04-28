import { axiosClient } from "../axiosClient.ts";
import { Plante } from "./plants.types.ts";

const API_URL = '/plantes';

// Fonction pour transformer les données du backend vers le frontend
function transformBackendToFrontend(data: any): Plante {
  return {
    id_plante: data.id_plante,
    nom: data.nom,
    point_de_vie: data.point_de_vie,
    attaque_par_seconde: data.attaque_par_seconde,
    degat_attaque: data.degat_attaque,
    cout: data.cout,
    soleil_par_seconde: data.soleil_par_seconde,
    effet: data.effet,
    chemin_image: data.chemin_image
  };
}

// Fonction pour transformer les données du frontend vers le backend
function transformFrontendToBackend(data: Plante): any {
  return {
    id_plante: data.id_plante,
    nom: data.nom,
    point_de_vie: data.point_de_vie,
    attaque_par_seconde: data.attaque_par_seconde,
    degat_attaque: data.degat_attaque,
    cout: data.cout,
    soleil_par_seconde: data.soleil_par_seconde,
    effet: data.effet,
    chemin_image: data.chemin_image
  };
}

export const plantsApi = {
  getAll: async (): Promise<Plante[]> => {
    const response = await axiosClient.get(API_URL);
    return response.data.map(transformBackendToFrontend);
  },

  getById: async (id: number): Promise<Plante> => {
    const response = await axiosClient.get(`${API_URL}/${id}`);
    return transformBackendToFrontend(response.data);
  },

  create: async (plante: Plante): Promise<Plante> => {
    const response = await axiosClient.post(API_URL, transformFrontendToBackend(plante));
    return transformBackendToFrontend(response.data);
  },

  update: async (id: number, plante: Plante): Promise<Plante> => {
    const response = await axiosClient.put(`${API_URL}/${id}`, transformFrontendToBackend(plante));
    return transformBackendToFrontend(response.data);
  },

  delete: async (id: number): Promise<void> => {
    await axiosClient.delete(`${API_URL}/${id}`);
  }
};
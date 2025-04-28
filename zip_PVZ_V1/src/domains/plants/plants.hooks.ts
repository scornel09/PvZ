import { useMutation } from '@tanstack/react-query';
import { plantsApi } from './plants.api';
import type { Plante } from './plants.types';

export const useGetPlants = () => {
  return useMutation({
    mutationFn: plantsApi.getAll,
  });
};

export const useGetPlant = () => {
  return useMutation({
    mutationFn: (id_plante: number) => plantsApi.getById(id_plante),
  });
};

export const useCreatePlant = () => {
  return useMutation({
    mutationFn: (planteData: Omit<Plante, 'id_plante'>) => {
      const newPlante: Plante = {
        ...planteData,
        id_plante: 0, // L'ID sera généré par le backend
      };
      return plantsApi.create(newPlante);
    },
  });
};

export const useUpdatePlant = () => {
  return useMutation({
    mutationFn: (params: { id_plante: number; data: Partial<Plante> }) => {
      const updatedPlante: Plante = {
        id_plante: params.id_plante,
        nom: params.data.nom ?? '',
        point_de_vie: params.data.point_de_vie ?? 0,
        temps_recharge: params.data.temps_recharge ?? 0,
        degat_attaque: params.data.degat_attaque ?? 0,
        cout: params.data.cout ?? 0,
        soleil_par_seconde: params.data.soleil_par_seconde ?? 0,
        effet: params.data.effet ?? 'NORMAL',
        chemin_image: params.data.chemin_image ?? '',
      };
      return plantsApi.update(params.id_plante, updatedPlante);
    },
  });
};

export const useDeletePlant = () => {
  return useMutation({
    mutationFn: (id_plante: number) => plantsApi.delete(id_plante),
  });
};
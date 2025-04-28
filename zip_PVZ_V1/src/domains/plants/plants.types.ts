// plants.types.ts
export interface Plante {
  id_plante: number;
  nom: string;
  point_de_vie: number;
  attaque_par_seconde: number;
  degat_attaque: number;
  cout: number;
  soleil_par_seconde: number;
  effet: string;
  chemin_image: string;
}

export enum PlanteEffet {
  NORMAL = 'NORMAL',
  SLOW_LOW = 'SLOW_LOW',
  SLOW_MEDIUM = 'SLOW_MEDIUM',
  SLOW_STOP = 'SLOW_STOP'
}
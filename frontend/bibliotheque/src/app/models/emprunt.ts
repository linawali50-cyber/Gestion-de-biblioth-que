
import { Livre } from "./livre";
import { Utilisateur } from "./utilisateur";

export interface Emprunt {
  id?: number;
  dateEmprunt: string;
  dateRetour?: string;
  retourne: boolean;
  utilisateur: Utilisateur;    
  livre: Livre;
}

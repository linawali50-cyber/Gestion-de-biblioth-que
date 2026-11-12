import { Utilisateur } from "./utilisateur";
import { Emprunt } from './emprunt';
export interface Adherent extends Utilisateur {
    emprunts?: Emprunt[];

}

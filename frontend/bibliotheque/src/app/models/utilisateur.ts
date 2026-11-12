import { Role } from "./role";

export interface Utilisateur {
  id?: number;
  nom: string;
  email: string;
  password?: string;
  isActive: boolean;
  role: Role;
}

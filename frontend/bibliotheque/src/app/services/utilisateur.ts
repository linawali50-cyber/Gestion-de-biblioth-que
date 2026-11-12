import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Utilisateur } from '../models/utilisateur';
import { Role } from '../models/role';

@Injectable({ providedIn: 'root' })
export class UtilisateurService {
  private apiUrl = `${environment.apiUrl}/utilisateurs`;

  constructor(private http: HttpClient) {}

  getAllUtilisateurs(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(`${this.apiUrl}/admin/tous`);
  }

inscrire(utilisateur: Utilisateur): Observable<Utilisateur> {
  return this.http.post<Utilisateur>(`${this.apiUrl}/inscrire`, utilisateur);
}

  getAdherents(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(`${this.apiUrl}/admin/adherents`);
  }

  getBibliothecaires(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(`${this.apiUrl}/admin/bibliothecaires`);
  }

  validerCompte(id: number): Observable<Utilisateur> {
    return this.http.put<Utilisateur>(`${this.apiUrl}/admin/valider/${id}`, {});
  }

  changerRole(id: number, role: Role): Observable<Utilisateur> {
    return this.http.put<Utilisateur>(`${this.apiUrl}/admin/role/${id}?role=${role}`, {});
  }

  ajouterUtilisateur(utilisateur: Utilisateur): Observable<Utilisateur> {
    return this.http.post<Utilisateur>(`${this.apiUrl}/admin/ajouter`, utilisateur);
  }

  supprimerUtilisateur(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/admin/${id}`);
  }

  // ====================== BIBLIOTHECAIRE ======================
  getAdherentById(id: number): Observable<Utilisateur> {
    return this.http.get<Utilisateur>(`${this.apiUrl}/bibliothecaire/adherent/${id}`);
  }

  updateAdherent(id: number, utilisateur: Partial<Utilisateur>): Observable<Utilisateur> {
    return this.http.put<Utilisateur>(`${this.apiUrl}/bibliothecaire/adherent/${id}`, utilisateur);
  }

  // ====================== COMMUN (Tous les rôles) ======================
  getProfil(id: number): Observable<Utilisateur> {
    return this.http.get<Utilisateur>(`${this.apiUrl}/${id}`);
  }

  modifierProfil(id: number, utilisateur: Partial<Utilisateur>): Observable<Utilisateur> {
    return this.http.put<Utilisateur>(`${this.apiUrl}/${id}/profil`, utilisateur);
  }
}
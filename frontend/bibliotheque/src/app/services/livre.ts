import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Livre } from '../models/livre';

@Injectable({ providedIn: 'root' })
export class LivreService {
  private apiUrl = `${environment.apiUrl}/livres`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Livre[]> {
    return this.http.get<Livre[]>(this.apiUrl);
  }

  getById(id: number): Observable<Livre> {
    return this.http.get<Livre>(`${this.apiUrl}/${id}`);
  }

  getByTitre(titre: string): Observable<Livre[]> {
    return this.http.get<Livre[]>(`${this.apiUrl}/titre/${titre}`);
  }

  getByAuteur(auteur: string): Observable<Livre[]> {
    return this.http.get<Livre[]>(`${this.apiUrl}/auteur/${auteur}`);
  }

  getByCategorie(categorie: string): Observable<Livre[]> {
    return this.http.get<Livre[]>(`${this.apiUrl}/categorie/${categorie}`);
  }
  getDisponibles(): Observable<Livre[]> {
    return this.http.get<Livre[]>(`${this.apiUrl}/disponibles`);
  }

  ajouter(livre: Livre): Observable<Livre> {
    return this.http.post<Livre>(this.apiUrl, livre);
  }
  modifier(id: number, livre: Livre): Observable<Livre> {
    return this.http.put<Livre>(`${this.apiUrl}/${id}`, livre);
  }
  supprimer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
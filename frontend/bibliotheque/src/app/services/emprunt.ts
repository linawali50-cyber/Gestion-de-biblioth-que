import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Emprunt } from '../models/emprunt';

@Injectable({ providedIn: 'root' })
export class EmpruntService {
  private apiUrl = `${environment.apiUrl}/emprunts`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Emprunt[]> {
    return this.http.get<Emprunt[]>(this.apiUrl);
  }

  getById(id: number): Observable<Emprunt> {
    return this.http.get<Emprunt>(`${this.apiUrl}/${id}`);
  }

  getByAdherent(adherentId: number): Observable<Emprunt[]> {
    return this.http.get<Emprunt[]>(`${this.apiUrl}/adherent/${adherentId}`);
  }

  getNonRetournes(): Observable<Emprunt[]> {
    return this.http.get<Emprunt[]>(`${this.apiUrl}/NonRetourner`);
  }

  getHistorique(adherentId: number): Observable<Emprunt[]> {
    return this.http.get<Emprunt[]>(`${this.apiUrl}/historique/${adherentId}`);
  }

emprunter(adherentId: number, livreId: number, dateRetour: string): Observable<Emprunt> {
  return this.http.post<Emprunt>(
    `${this.apiUrl}/emprunter/${adherentId}/${livreId}?dateRetour=${dateRetour}`,{}   
  );
}

  validerRetour(id: number): Observable<Emprunt> {
    return this.http.put<Emprunt>(`${this.apiUrl}/retour/${id}`, {});
  }
}
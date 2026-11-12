import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { Utilisateur } from '../models/utilisateur';
import { Role } from '../models/role';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private apiUrl = `${environment.apiUrl}/utilisateurs`;

  private currentUserSignal = signal<Utilisateur | null>(null);

  constructor(private http: HttpClient) {
    const savedUser = localStorage.getItem('currentUser');
    if (savedUser) this.currentUserSignal.set(JSON.parse(savedUser));
  }

  get currentUser() {
    return this.currentUserSignal.asReadonly();
  }

  inscrire(utilisateur: Utilisateur): Observable<Utilisateur> {
    return this.http.post<Utilisateur>(`${this.apiUrl}/inscrire`, utilisateur);
  }

  login(email: string, password: string): Observable<Utilisateur> {
    return this.http.post<Utilisateur>(
      `${this.apiUrl}/login?email=${email}&password=${password}`, {}
    ).pipe(
      tap(user => {
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.currentUserSignal.set(user);
      })
    );
  }

  logout(): void {
    localStorage.removeItem('currentUser');
    this.currentUserSignal.set(null);
  }

  isLoggedIn(): boolean {
    return this.currentUserSignal() !== null;
  }

  hasRole(role: Role): boolean {
    return this.currentUserSignal()?.role === role;
  }
}

import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { Livre } from '../../models/livre';
import { Emprunt } from '../../models/emprunt';
import { LivreService } from '../../services/livre';
import { EmpruntService } from '../../services/emprunt';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-adherent-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './adherent-dashboard.html',
  styleUrls: ['./adherent-dashboard.css']
})
export class AdherentDashboard implements OnInit {

  private livreService = inject(LivreService);
  private empruntService = inject(EmpruntService);
  private authService = inject(AuthService);

  livresDisponibles = signal<Livre[]>([]);
  mesEmprunts = signal<Emprunt[]>([]);
  loading = signal(true);

  ngOnInit() {
    const user = this.authService.currentUser();
    if (!user || user.role !== 'ADHERENT') {
      window.location.href = '/login';
      return;
    }

    // Livres disponibles
    this.livreService.getDisponibles().subscribe({
      next: (livres) => this.livresDisponibles.set([...livres]),
      error: (err) => console.error('Erreur chargement livres disponibles', err)
    });

    // Mes emprunts
    if (user.id !== undefined) {
      this.empruntService.getByAdherent(user.id).subscribe({
        next: (emprunts) => {
          console.log('Données reçues :', emprunts);
          this.mesEmprunts.set([...(emprunts || [])]);   
          this.loading.set(false);
        },
        error: (err) => {
          console.error('Erreur chargement mes emprunts', err);
          this.mesEmprunts.set([]);
          this.loading.set(false);
        }
      });
    } else {
      this.mesEmprunts.set([]);
      this.loading.set(false);
    }
  }
}



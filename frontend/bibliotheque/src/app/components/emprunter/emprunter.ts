import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { LivreService } from '../../services/livre';
import { EmpruntService } from '../../services/emprunt';
import { AuthService } from '../../services/auth';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-emprunter',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './emprunter.html',
  styleUrls: ['./emprunter.css']
})
export class Emprunter implements OnInit {
  private route = inject(ActivatedRoute);
  private livreService = inject(LivreService);
  private empruntService = inject(EmpruntService);
  private authService = inject(AuthService);
  private router = inject(Router);

  livreId = signal(0);
  livre = signal<any>(null);
  message = signal('');
  loading = signal(true);
today: string = new Date().toISOString().split('T')[0];

maxDate: string;

dateRetour: string | null = null;

dateError: boolean = false;

constructor() {
  const max = new Date();
  max.setDate(max.getDate() + 14);
  this.maxDate = max.toISOString().split('T')[0];
}
  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.livreId.set(id);

    this.livreService.getById(id).subscribe({
      next: (l) => {
        this.livre.set(l);
        this.loading.set(false);
      },
      error: () => this.loading.set(false)
    });
  }

confirmerEmprunt() {
  const user = this.authService.currentUser();

  if (!user || user.role !== 'ADHERENT' || !user.id) {
    this.message.set("Vous devez être connecté en tant qu'adhérent pour emprunter.");
    return;
  }
  if (!this.dateRetour) {
    this.message.set("❌ Veuillez choisir une date de retour.");
    return;
  }

  const today = new Date();
  const retour = new Date(this.dateRetour);

  const diffDays = (retour.getTime() - today.getTime()) / (1000 * 3600 * 24);

  if (diffDays < 0 || diffDays > 14) {
    this.message.set("❌ La date de retour doit être dans un maximum de 14 jours.");
    return;
  }

  this.empruntService.emprunter(user.id, this.livreId(),this.dateRetour).subscribe({
    next: () => {
      this.message.set('✅ Emprunt effectué avec succès !');
      setTimeout(() => this.router.navigate(['/adherent-dashboard']), 1800);
    },
    error: (err) => {
      this.message.set('❌ ' + (err.error?.message || 'Erreur lors de l\'emprunt'));
    }
  });
}
}

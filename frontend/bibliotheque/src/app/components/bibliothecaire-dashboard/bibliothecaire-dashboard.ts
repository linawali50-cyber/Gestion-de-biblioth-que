import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Emprunt } from '../../models/emprunt';
import { Livre } from '../../models/livre';

import { EmpruntService } from '../../services/emprunt';
import { LivreService } from '../../services/livre';

@Component({
  selector: 'app-bibliothecaire-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './bibliothecaire-dashboard.html',
  styleUrls: ['./bibliothecaire-dashboard.css']
})
export class BibliothecaireDashboard implements OnInit {

  private empruntService = inject(EmpruntService);
  private livreService = inject(LivreService);

  empruntsNonRetournes = signal<Emprunt[]>([]);
  loading = signal(true);

  // Formulaire d'ajout livre
  nouveauLivre: Livre = {
    id: 0,
    titre: '',
    auteur: '',
    categorie: '',
    disponible: true,
  };

  ngOnInit() {
    this.loadNonRetournes();
  }

  private loadNonRetournes() {
    this.empruntService.getNonRetournes().subscribe({
      next: (data) => {
        console.log('📊 Données reçues pour le tableau :', data);
        this.empruntsNonRetournes.set([...data]);   
        this.loading.set(false);
      },
      error: (err) => {
        console.error('Erreur chargement emprunts non retournés', err);
        this.empruntsNonRetournes.set([]);
        this.loading.set(false);
      }
    });
  }

  ajouterLivre() {
    if (!this.nouveauLivre.titre || !this.nouveauLivre.auteur) {
      alert("Le titre et l'auteur sont obligatoires");
      return;
    }

    this.livreService.ajouter(this.nouveauLivre as Livre).subscribe({
      next: (livreAjoute) => {
        alert(`✅ Livre "${livreAjoute.titre}" ajouté avec succès !`);
        this.nouveauLivre = { titre: '', auteur: '', categorie: '', disponible: true };
      },
      error: (err) => {
        console.error('Erreur ajout livre', err);
        alert('❌ Erreur lors de l\'ajout du livre');
      }
    });
  }

  validerRetour(id: number) {
    this.empruntService.validerRetour(id).subscribe({
      next: () => {
        alert('✅ Retour validé avec succès !');
        this.loadNonRetournes();   
      },
      error: (err) => {
        console.error('Erreur validation retour', err);
        alert('Erreur lors de la validation du retour');
      }
    });
  }
}
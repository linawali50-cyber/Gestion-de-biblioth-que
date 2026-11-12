import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';  
import { LivreService } from '../../../services/livre';
import { Livre } from '../../../models/livre';


@Component({
  selector: 'app-livre-list',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],   
  templateUrl: './livre-list.html',
})
export class LivreList implements OnInit {

  private livreService = inject(LivreService);

  livres = signal<Livre[]>([]);
  loading = signal(true);
  searchTerm: string = '';                   

  ngOnInit() {
    this.livreService.getAll().subscribe({
      next: (data) => {
        this.livres.set(data);
        this.loading.set(false);
      },
      error: () => this.loading.set(false)
    });
  }


  filteredLivres() {
    const term = this.searchTerm.toLowerCase().trim();
    
    if (!term) {
      return this.livres();
    }

    return this.livres().filter(livre => 
      livre.titre.toLowerCase().includes(term) ||
      livre.auteur.toLowerCase().includes(term) ||
      livre.categorie.toLowerCase().includes(term)
    );
  }
}

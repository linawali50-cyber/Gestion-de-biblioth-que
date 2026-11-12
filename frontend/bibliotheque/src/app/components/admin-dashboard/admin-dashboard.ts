import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Utilisateur } from '../../models/utilisateur';
import { UtilisateurService } from '../../services/utilisateur';
import { Role } from '../../models/role';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin-dashboard.html',
  styleUrls: ['./admin-dashboard.css'] 
})
export class AdminDashboard implements OnInit {

  private utilisateurService = inject(UtilisateurService);

  utilisateurs = signal<Utilisateur[]>([]);
  loading = signal(true);

  Role = Role;   

  ngOnInit() {
    this.loadUtilisateurs();
  }

  private loadUtilisateurs() {
    this.utilisateurService.getAllUtilisateurs().subscribe({
      next: (data) => {
        this.utilisateurs.set(data);
        this.loading.set(false);
      },
      error: () => this.loading.set(false)
    });
  }

  validerCompte(id: number) {
    this.utilisateurService.validerCompte(id).subscribe({
      next: () => {
        alert('✅ Compte validé avec succès !');
        this.loadUtilisateurs();   
      }
    });
  }

  changerRole(id: number, newRole: Role) {
    this.utilisateurService.changerRole(id, newRole).subscribe({
      next: () => {
        alert(`✅ Rôle changé en ${newRole} avec succès !`);
        this.loadUtilisateurs();
      }
    });
  }

  supprimer(id: number) {
    if (confirm('⚠️ Voulez-vous vraiment supprimer cet utilisateur ?')) {
      this.utilisateurService.supprimerUtilisateur(id).subscribe({
        next: () => {
          alert('✅ Utilisateur supprimé avec succès');
          this.loadUtilisateurs();
        }
      });
    }
  }

  ajouterUtilisateur() {
    const nom = prompt("Nom de l'utilisateur :");
    const prenom = prompt("Prénom de l'utilisateur :");
    const email = prompt("Email de l'utilisateur :");
    const password = prompt("Mot de passe :");

    if (!email || !password) {
      alert("Email et mot de passe sont obligatoires");
      return;
    }

    const newUser: Partial<Utilisateur> = {
      nom: nom || '',
      email: email,
      password: password,
      role: Role.ADHERENT   
    };

    this.utilisateurService.ajouterUtilisateur(newUser as Utilisateur).subscribe({
      next: (user) => {
        alert(`✅ Utilisateur  ${user.nom} ajouté avec succès !`);
        this.loadUtilisateurs();
      },
      error: (err) => {
        alert("❌ Erreur lors de l'ajout : " + (err.error?.message || 'Vérifiez les données'));
      }
    });
  }
}
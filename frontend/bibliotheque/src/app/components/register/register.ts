import { Component } from '@angular/core';
import { UtilisateurService } from '../../services/utilisateur';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Role } from '../../models/role';

@Component({
  selector: 'app-register',
  imports: [CommonModule, FormsModule,RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  nom = '';
  email = '';
  password = '';
  success = false;
  error = '';

  constructor(private utilisateurService: UtilisateurService, private router: Router) {}

onSubmit() {
  if (!this.nom || !this.email || !this.password) {
    this.error = "Champs obligatoires";
    return;
  }

 this.utilisateurService.inscrire({
  nom: this.nom,
  email: this.email,
  password: this.password,
  isActive: true,
  role: Role.ADHERENT
}).subscribe({
  next: () => {
    console.log(this.nom, this.email, this.password);
    this.success = true;
  },
  error: () =>{ this.error = "Erreur";
    console.log(this.nom, this.email, this.password);
   }
});
}

}

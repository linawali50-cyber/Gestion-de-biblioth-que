import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule,RouterLink],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class Login {
  private authService = inject(AuthService);
  private router = inject(Router);

  email = '';
  password = '';
  error = signal<string>('');

  onLogin() {
    this.authService.login(this.email, this.password).subscribe({
      next: (user) => {
        if (user.role === 'ADMIN') {
          this.router.navigate(['/admin']);
        } else if (user.role === 'BIBLIOTHECAIRE') {
          this.router.navigate(['/bibliothecaire-dashboard']);
        } else {
          this.router.navigate(['/adherent-dashboard']);
        }
      },
      error: () => this.error.set('Email ou mot de passe incorrect')
    });
  }
}

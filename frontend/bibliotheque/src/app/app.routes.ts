import { Routes } from '@angular/router';

import { Home } from './components/home/home';
import { Login } from './components/login/login';
import { Register} from './components/register/register';
import { LivreList } from './components/livres/livre-list/livre-list';
import { Emprunter } from './components/emprunter/emprunter';
import { AdherentDashboard } from './components/adherent-dashboard/adherent-dashboard';
import { BibliothecaireDashboard } from './components/bibliothecaire-dashboard/bibliothecaire-dashboard';
import { AdminDashboard } from './components/admin-dashboard/admin-dashboard';

export const routes: Routes = [
  { path: '', component: Home, title: 'Accueil' },
  { path: 'login', component: Login, title: 'Connexion' },
  { path: 'register', component: Register, title: 'Inscription' },
  { path: 'livres', component: LivreList, title: 'Livres' },
  { path: 'emprunter/:id', component: Emprunter, title: 'Emprunter' },
  { path: 'adherent-dashboard', component: AdherentDashboard, title: 'adherent-dashboard' },
  { path: 'bibliothecaire-dashboard', component: BibliothecaireDashboard, title: 'bibliothecaire-dashboard' },
  { path: 'admin', component: AdminDashboard, title: 'admin' },

  { path: '**', redirectTo: '' }
];


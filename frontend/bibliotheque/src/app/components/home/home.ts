import { Component, Inject } from '@angular/core';
import { AuthService } from '../../services/auth';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [RouterLink],
  providers: [AuthService],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
    constructor(@Inject(AuthService) public authService: AuthService) {}
}

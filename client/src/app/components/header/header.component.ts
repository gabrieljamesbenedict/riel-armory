import { Component, effect, inject, signal } from '@angular/core';
import { Router, RouterLink } from "@angular/router";
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-header',
  imports: [RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {

  menuOpen = false;

  private authService: AuthService = inject(AuthService);
  private router: Router = inject(Router)

  token = this.authService.token;

  constructor() {}

  logoutUser() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}

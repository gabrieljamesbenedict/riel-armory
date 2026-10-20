import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../service/auth.service';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, RouterOutlet], 
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent {

  username = signal('');
  email = signal('');
  password = signal('');
  confirmPassword = signal('');
  
  isLoading = signal(false);
  errorMessage = signal('');

  private authService: AuthService = inject(AuthService)
  private router: Router = inject(Router)

  onSignup(): void {
    this.errorMessage.set('');
    
    if (!this.username() || !this.email() || !this.password() || !this.confirmPassword()) {
      this.errorMessage.set('All fields are required.');
      return;
    }

    if (!(this.email().includes("@") && this.email().includes("."))) {
      this.errorMessage.set('Invalid email address format.');
      return;
    }

    if (this.password() !== this.confirmPassword()) {
      this.errorMessage.set('Passwords do not match. Please verify.');
      this.password.set('');
      this.confirmPassword.set('');
      return;
    }

    if (this.password().length < 6) {
      this.errorMessage.set('Password must be at least 6 characters long.');
      return;
    }

    this.authService.register(this.username(), this.password(), this.email()).subscribe({
      next: (response) => {
        const message = response;
        this.router.navigate(['/home']);
      },
      error: (err) => {
        console.error(err);
        const message = typeof err.error === 'string' ? err.error : err.error?.message || 'Registration failed';
        this.errorMessage.set(message);
      }
    })
  }
}

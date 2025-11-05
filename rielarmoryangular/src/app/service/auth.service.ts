import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  private apiUrl = '/api/auth';
  private tokenSignal = signal<string | null>(localStorage.getItem('token'));
  public token = this.tokenSignal.asReadonly();

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, { username, password })
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
          this.tokenSignal.set(response.token);
        })
      );
  }

  register(username: string, password: string, email: string) {
    const roleId = 2; // 2 = Customer
    return this.http.post<any>(`${this.apiUrl}/register`, { username, password , email, roleId});
  }

  logout() {
    localStorage.removeItem('token');
    this.tokenSignal.set(null);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }
}

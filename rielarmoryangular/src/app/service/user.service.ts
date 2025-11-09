import { HttpClient } from '@angular/common/http';
import { User } from '../model/user.model';
import { inject, Injectable } from '@angular/core';
import { Observable, Observer } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = '/api/users';

  private http: HttpClient = inject(HttpClient)

  constructor() {}

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/all`);
  }

  getUserById(userId: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${userId}`);
  }
}

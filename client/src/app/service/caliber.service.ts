import { inject, Injectable } from '@angular/core';
import { Caliber } from '../model/caliber.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CaliberService {

  private apiUrl = '/api/calibers';

  private http: HttpClient = inject(HttpClient)

  constructor() {}

  getAllCalibers(): Observable<Caliber[]> {
    return this.http.get<Caliber[]>(`${this.apiUrl}/all`);
  }
}

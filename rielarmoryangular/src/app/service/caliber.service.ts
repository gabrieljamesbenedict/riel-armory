import { Injectable } from '@angular/core';
import { Caliber } from '../model/caliber.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CaliberService {

  private apiUrl = '/api/calibers';

  constructor(private http: HttpClient) {}

  getAllCalibers(): Observable<Caliber[]> {
    return this.http.get<Caliber[]>(`${this.apiUrl}/all`);
  }
}

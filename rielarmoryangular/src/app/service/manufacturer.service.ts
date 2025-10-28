import { Injectable } from '@angular/core';
import { Manufacturer } from '../model/manufacturer.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ManufacturerService {

private apiUrl = '/api/manufacturers';

  constructor(private http: HttpClient) { }

  getAllManufacturers(): Observable<Manufacturer[]> {
    return this.http.get<Manufacturer[]>(`${this.apiUrl}/all`);
  }
}

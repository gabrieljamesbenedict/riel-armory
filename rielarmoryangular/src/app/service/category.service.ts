import { inject, Injectable } from '@angular/core';
import { Category } from '../model/category.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private apiUrl = '/api/categories';

  private http: HttpClient = inject(HttpClient)

  constructor() {}

  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.apiUrl}/all`);
  }
}

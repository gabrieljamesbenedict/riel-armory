import { inject, Injectable } from '@angular/core';
import { Tag } from '../model/tag.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TagService {

  private apiUrl = '/api/tag';

  private http: HttpClient = inject(HttpClient)

  constructor() {}

  getAllTags(): Observable<Tag[]> {
    return this.http.get<Tag[]>(`${this.apiUrl}/all`);
  }
}

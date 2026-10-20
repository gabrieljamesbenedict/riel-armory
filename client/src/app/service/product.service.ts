import { inject, Injectable } from '@angular/core';
import { Product } from '../model/product.model';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = '/api/products';

  private http: HttpClient = inject(HttpClient)

  constructor() {}

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl}/all`);
  }

  updateProduct(productId: number, requestProduct: Product): Observable<Product> {
    console.log("UPDATE PRODUCT: " + `${this.apiUrl}/`+productId)
    return this.http.put<Product>(`${this.apiUrl}/`+productId, requestProduct);
  }
}


import { inject, Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';
import { take, map, switchMap } from 'rxjs/operators';

import { ProductService } from './product.service';
import { Product } from '../model/product.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  private router: Router = inject(Router)
  private productService = inject(ProductService)
  private http: HttpClient = inject(HttpClient)

  constructor() { }

  submitSearch(form: NgForm) {
    const searchTerm = form.value.query; 

    if (searchTerm) {
      this.router.navigate(['/store/search'], {
        queryParams: { q: searchTerm } 
      });
      
      form.resetForm({ query: '' }); 
    }
  }

  selectCategory(selected: String) {
    this.router.navigate(['/store/search'], {
      queryParams: { q: selected } 
    });
  }

  selectManufacturer(selected: String) {
    this.router.navigate(['/store/search'], {
      queryParams: { q: selected } 
    });
  }

  selectCaliber(selected: String) {
    this.router.navigate(['/store/search'], {
      queryParams: { q: selected } 
    });
  }

  viewProductDetails(boughtProduct: Product) {
    if (boughtProduct.stock > 0) {
      alert("You have bought: " + boughtProduct.name)
      boughtProduct.stock = boughtProduct.stock - 1
      this.productService.updateProduct(boughtProduct.productId, boughtProduct).subscribe()
    } else {
      alert("Out of stock")
    }
  }

  uploadProductImage(productId: number, file: File) {

    const apiUrl = '/api/products';
    
    const formData = new FormData();
    formData.append('file', file);

    return this.http.post(
      `${apiUrl}/image/upload/${productId}`,
      formData,
      { responseType: 'text', reportProgress: true, observe: 'events' }
    );

  }
  
}

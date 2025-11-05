import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';
import { take, map, switchMap } from 'rxjs/operators';

import { CategoryService } from '../../../service/category.service';
import { ManufacturerService } from '../../../service/manufacturer.service';
import { CaliberService } from '../../../service/caliber.service';
import { ProductService } from '../../../service/product.service';

import { Category } from '../../../model/category.model';
import { Manufacturer } from '../../../model/manufacturer.model';
import { Caliber } from '../../../model/caliber.model';
import { Product } from '../../../model/product.model';

import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-store',
  imports: [FormsModule, CurrencyPipe], 
  templateUrl: '../store.component.html',
  styleUrl: '../store.component.scss'
})
export class SearchResultComponent implements OnInit {

  private router = inject(Router); 
  private route = inject(ActivatedRoute);
  
  private categoryService = inject(CategoryService);
  private manufacturerService = inject(ManufacturerService);
  private caliberService = inject(CaliberService);
  private productService = inject(ProductService);

  public categories: Category[] = []
  public manufacturers: Manufacturer[] = []
  public calibers: Caliber[] = []
  public products: Product[] = []

  constructor() {}

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


  ngOnInit(): void {

    this.categoryService.getAllCategories().subscribe({
      next: (data) => {
        this.categories = data;
      },
      error: (err) => {
        console.error('Error fetching categories:', err);
      }
    })

    this.manufacturerService.getAllManufacturers().subscribe({
      next: (data) => {
        this.manufacturers = data;
      },
      error: (err) => {
        console.error('Error fetching categories:', err);
      }
    })

    this.caliberService.getAllCalibers().subscribe({
      next: (data) => {
        this.calibers = data;
      },
      error: (err) => {
        console.error('Error fetching categories:', err);
      }
    })

    this.route.queryParams.pipe(
      switchMap(params => {
        const searchTerm = params['q'] || '';
        const lowerCaseSearchTerm = searchTerm.toLowerCase();

        return this.productService.getAllProducts()
          .pipe(
            map(
              (incomingProducts: Product[]) => incomingProducts.filter(
                incomingProduct => 
                  incomingProduct.name.toLowerCase().includes(lowerCaseSearchTerm)
                  || incomingProduct.caliberName.toLowerCase().includes(lowerCaseSearchTerm)
                  || incomingProduct.manufacturerName.toLowerCase().includes(lowerCaseSearchTerm)
                  || incomingProduct.categoryName.toLowerCase().includes(lowerCaseSearchTerm)
              )
            )
          );
      })
    ) // Subscribe to the final filtered Observable
    .subscribe({
      next: (data) => {
        this.products = data;
      },
      error: (err) => {
        console.error('Error fetching products:', err);
      }
    });
    
  }
}
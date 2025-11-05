import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';
import { map, switchMap } from 'rxjs/operators';

import { CategoryService } from '../../service/category.service';
import { ManufacturerService } from '../../service/manufacturer.service';
import { CaliberService } from '../../service/caliber.service';
import { ProductService } from '../../service/product.service';

import { Category } from '../../model/category.model';
import { Manufacturer } from '../../model/manufacturer.model';
import { Caliber } from '../../model/caliber.model';
import { Product } from '../../model/product.model';

import { StoreService } from '../../service/store.service';

import { CommonModule, CurrencyPipe, NgIf } from '@angular/common';
import { HttpEventType } from '@angular/common/http';

@Component({
  selector: 'app-store',
  imports: [FormsModule, CurrencyPipe], 
  templateUrl: './store.component.html',
  styleUrl: './store.component.scss'
})
export class StoreComponent implements OnInit {

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

  public storeService: StoreService = inject(StoreService)

  public apiUrl = "/api/products/image";

  selectedFile: File | null = null;
  uploadingProductId: number | null = null;
  uploadProgress: number = 0;

  constructor() {}


  onFileSelected(event: Event, productId: number): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
      this.uploadingProductId = productId;
    }
  }


  uploadImage(): void {
    if (!this.selectedFile || !this.uploadingProductId) {
      alert('Please select a file first.');
      return;
    }

    this.storeService.uploadProductImage(this.uploadingProductId, this.selectedFile).subscribe({
      next: (event) => {
        if (event.type === HttpEventType.UploadProgress) {
          this.uploadProgress = Math.round((100 * event.loaded) / (event.total || 1));
        } else if (event.type === HttpEventType.Response) {
          //alert('Image uploaded successfully!');
          this.uploadProgress = 0;
          this.selectedFile = null;
          this.uploadingProductId = null;
          // Optional: refresh product list
          this.ngOnInit();
        }
      },
      error: (err) => {
        console.error('Upload failed:', err);
        alert('Failed to upload image.');
        this.uploadProgress = 0;
      }
    });
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
                  || incomingProduct.tagNames.includes(lowerCaseSearchTerm)
              )
            )
          );
      })
    )
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
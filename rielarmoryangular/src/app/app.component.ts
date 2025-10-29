import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductService } from './service/product.service';
import { Product } from './model/product.model';
import { ManufacturerService } from './service/manufacturer.service';
import { Manufacturer } from './model/manufacturer.model';
import { CategoryService } from './service/category.service';
import { Category } from './model/category.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent implements OnInit {
  title = 'Riel\'s Armory';

  products: Product[] = [];
  manufacturers: Manufacturer[] = [];
  categories: Category[] = [];

  // constructor(
  //   private productService: ProductService,
  //   private manufacturerService: ManufacturerService,
  //   private categoryService: CategoryService
  // ) {}

  constructor() {}

  private productService = inject(ProductService);
  private manufacturerService = inject(ManufacturerService);
  private categoryService = inject(CategoryService);

  ngOnInit(): void {
    console.log('AppComponent initialized');

    this.productService.getAllProducts().subscribe({
      next: (data) => {
        console.log('Products fetched:', data);
        this.products = data;
      },
      error: (err) => console.error('Error fetching products:', err)
    });

    this.manufacturerService.getAllManufacturers().subscribe({
      next: (data) => {
        console.log('Manufacturers fetched:', data);
        this.manufacturers = data;
      },
      error: (err) => console.error('Error fetching products:', err)
    });

    this.categoryService.getAllCategories().subscribe({
      next: (data) => {
        console.log('Categories fetched:', data);
        this.categories = data;
      },
      error: (err) => console.error('Error fetching products:', err)
    });

  }
}
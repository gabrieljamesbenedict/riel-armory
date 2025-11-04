import { Component, inject, OnInit } from '@angular/core';
import { CategoryService } from '../../service/category.service';
import { ManufacturerService } from '../../service/manufacturer.service';

import { Category } from '../../model/category.model';
import { Manufacturer } from '../../model/manufacturer.model';

@Component({
  selector: 'app-store',
  imports: [],
  templateUrl: './store.component.html',
  styleUrl: './store.component.scss'
})
export class StoreComponent implements OnInit {
  
  private categoryService = inject(CategoryService);
  private manufacturerService = inject(ManufacturerService);

  public categories: Category[] = []
  public manufacturers: Manufacturer[] = []

  constructor() {}

  ngOnInit(): void {
      this.categoryService.getAllCategories().subscribe({
        next: (data) => {
          this.categories = data;
        },
        error: (err) => {
        console.error('Error fetching categories:', err);
        }
      })
  }

}

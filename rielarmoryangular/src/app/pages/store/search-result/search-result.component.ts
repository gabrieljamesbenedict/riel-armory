import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';

import { CategoryService } from '../../../service/category.service';
import { ManufacturerService } from '../../../service/manufacturer.service';
import { CaliberService } from '../../../service/caliber.service';

import { Category } from '../../../model/category.model';
import { Manufacturer } from '../../../model/manufacturer.model';
import { Caliber } from '../../../model/caliber.model';


@Component({
  selector: 'app-search-result',
  imports: [FormsModule],
  templateUrl: './search-result.component.html',
  styleUrl: '../store.component.scss'
})
export class SearchResultComponent {

  private router = inject(Router); 
  
  private categoryService = inject(CategoryService);
  private manufacturerService = inject(ManufacturerService);
  private caliberService = inject(CaliberService);

  public categories: Category[] = []
  public manufacturers: Manufacturer[] = []
  public calibers: Caliber[] = []

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
  }
}

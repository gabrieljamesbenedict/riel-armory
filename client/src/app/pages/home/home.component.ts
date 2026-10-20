import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from "@angular/router";

import { ProductService } from '../../service/product.service';
import { Product } from '../../model/product.model';
import { CurrencyPipe } from '@angular/common';

import { StoreService } from '../../service/store.service';

@Component({
  selector: 'app-home',
  imports: [RouterLink, CurrencyPipe],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit{

  private productService: ProductService = inject(ProductService)
  storeService: StoreService = inject(StoreService)
  products: Product[] = []

  ngOnInit(): void {
      this.productService.getAllProducts().subscribe({
    next: (data) => {
      const amount = 6
      let i = 0
      while (i < amount) {
        const shuffled = [...data].sort(() => Math.random() - 0.5);
        this.products.push(...shuffled.slice(0, amount));
        console.log(this.products[this.products.length].name)
        i++
      }
    },
    error: (err) => {
      console.error('Error fetching products:', err);
    }
  })
  }

}

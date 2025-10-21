import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductService, Product } from './services/product.service';

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
  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    console.log('AppComponent initialized');
    this.productService.getAllProducts().subscribe({
      next: (data) => {
        console.log('Products fetched:', data);
        this.products = data;
      },
      error: (err) => console.error('Error fetching products:', err)
    });
  }
}

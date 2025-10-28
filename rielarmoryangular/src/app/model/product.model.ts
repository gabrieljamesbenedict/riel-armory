import { Category } from "./category.model";
import { Manufacturer } from "./manufacturer.model";

export interface Product {
  productId: number;
  name: string;
  description: string;
  stock: number;
  price: number;
  manufacturer: Manufacturer;
  category: Category
  caliber: number;
  magazineCapacity: number;
}
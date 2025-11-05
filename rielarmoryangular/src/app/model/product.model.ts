import { Category } from "./category.model";
import { Manufacturer } from "./manufacturer.model";

export interface Product {
  productId: number; 
  name: string;
  description: string;
  stock: number;
  price: number;
  manufacturerName: string;
  categoryName: string;
  tagNames: string[];
  caliberName: string;
  magazineCapacity: number;
  imageName: string;
}
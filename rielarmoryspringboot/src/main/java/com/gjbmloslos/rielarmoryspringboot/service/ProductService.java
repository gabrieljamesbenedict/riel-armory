package com.gjbmloslos.rielarmoryspringboot.service;

import com.gjbmloslos.rielarmoryspringboot.model.Product;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {
    Product createProduct(Product request);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product updateProduct(Long id, Product request);
    void deleteProduct(Long id);
}

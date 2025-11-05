package com.gjbmloslos.rielarmoryspringboot.service;

import com.gjbmloslos.rielarmoryspringboot.dto.ProductRequest;
import com.gjbmloslos.rielarmoryspringboot.dto.ProductResponse;
import com.gjbmloslos.rielarmoryspringboot.model.Product;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(Long id);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
}


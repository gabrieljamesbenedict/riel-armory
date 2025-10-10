package com.gjbmloslos.rielarmoryspringboot.service;

import com.gjbmloslos.rielarmoryspringboot.model.dto.ProductRequest;
import com.gjbmloslos.rielarmoryspringboot.model.dto.ProductResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(com.gjbmloslos.rielarmoryspringboot.model.dto.@Valid ProductRequest request);
    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(Long id);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
}

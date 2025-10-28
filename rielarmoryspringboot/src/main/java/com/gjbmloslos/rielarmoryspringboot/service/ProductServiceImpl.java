package com.gjbmloslos.rielarmoryspringboot.service;

import com.gjbmloslos.rielarmoryspringboot.model.Product;
import com.gjbmloslos.rielarmoryspringboot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    // TODO createProduct() method
    @Override
    public Product createProduct(Product request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();
        Product saved = productRepository.save(product);
        return toResponse(saved);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    @Override
    public Product updateProduct(Long id, Product request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        return toResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }

    private Product toResponse(Product product) {
        return new Product(product.getId(), product.getName(),
                product.getDescription(), product.getPrice(), product.getStock());
    }
}

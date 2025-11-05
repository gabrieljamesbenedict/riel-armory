package com.gjbmloslos.rielarmoryspringboot.serviceImpl;

import com.gjbmloslos.rielarmoryspringboot.dto.ProductRequest;
import com.gjbmloslos.rielarmoryspringboot.dto.ProductResponse;
import com.gjbmloslos.rielarmoryspringboot.model.Product;
import com.gjbmloslos.rielarmoryspringboot.model.Manufacturer;
import com.gjbmloslos.rielarmoryspringboot.model.Category;
import com.gjbmloslos.rielarmoryspringboot.model.Caliber;
import com.gjbmloslos.rielarmoryspringboot.model.Tag;
import com.gjbmloslos.rielarmoryspringboot.repository.ProductRepository;
import com.gjbmloslos.rielarmoryspringboot.repository.ManufacturerRepository;
import com.gjbmloslos.rielarmoryspringboot.repository.CategoryRepository;
import com.gjbmloslos.rielarmoryspringboot.repository.CaliberRepository;
import com.gjbmloslos.rielarmoryspringboot.repository.TagRepository;
import com.gjbmloslos.rielarmoryspringboot.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final CategoryRepository categoryRepository;
    private final CaliberRepository caliberRepository;
    private final TagRepository tagRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Manufacturer manufacturer = manufacturerRepository.findById(request.getManufacturerId())
                .orElseThrow(() -> new RuntimeException("Manufacturer not found"));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Caliber caliber = caliberRepository.findById(request.getCaliberId())
                .orElseThrow(() -> new RuntimeException("Caliber not found"));

        Set<Tag> tags = request.getTagIds() != null
                ? new HashSet<Tag>(tagRepository.findAllById(request.getTagIds()))
                : Collections.emptySet();

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .stock(request.getStock())
                .price(request.getPrice())
                .manufacturer(manufacturer)
                .category(category)
                .caliber(caliber)
                .tags(tags)
                .magazineCapacity(request.getMagazineCapacity())
                .build();

        Product savedProduct = productRepository.save(product);
        return toResponse(savedProduct);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        return toResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));

        if (request.getName() != null) product.setName(request.getName());
        if (request.getDescription() != null) product.setDescription(request.getDescription());
        if (request.getPrice() != null) product.setPrice(request.getPrice());
        if (request.getStock() != null) product.setStock(request.getStock());
        if (request.getManufacturerId() != null)
            product.setManufacturer(manufacturerRepository.findById(request.getManufacturerId())
                    .orElseThrow(() -> new RuntimeException("Manufacturer not found")));
        if (request.getCategoryId() != null)
            product.setCategory(categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found")));
        if (request.getCaliberId() != null)
            product.setCaliber(caliberRepository.findById(request.getCaliberId())
                    .orElseThrow(() -> new RuntimeException("Caliber not found")));
        if (request.getTagIds() != null) {
            Set<Tag> tags = new HashSet<>(tagRepository.findAllById(request.getTagIds()));
            product.setTags(tags);
        }
        if (request.getMagazineCapacity() != null) product.setMagazineCapacity(request.getMagazineCapacity());

        Product updatedProduct = productRepository.save(product);
        return toResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }

    private ProductResponse toResponse(Product product) {
        if (product == null) return null;
        return ProductResponse.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .stock(product.getStock())
                .price(product.getPrice())
                .manufacturerName(product.getManufacturer() != null ? product.getManufacturer().getName() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .caliberName(product.getCaliber() != null ? product.getCaliber().getName() : null)
                .magazineCapacity(product.getMagazineCapacity())
                .tagNames(product.getTags() != null
                        ? product.getTags().stream().map(Tag::getName).collect(Collectors.toSet())
                        : Collections.emptySet())
                .build();
    }
}

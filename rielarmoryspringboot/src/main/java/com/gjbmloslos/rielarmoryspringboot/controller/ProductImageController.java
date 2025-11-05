package com.gjbmloslos.rielarmoryspringboot.controller;

import com.gjbmloslos.rielarmoryspringboot.model.Product;
import com.gjbmloslos.rielarmoryspringboot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductRepository productRepository; // use final with @RequiredArgsConstructor
    private final Path uploadDirectory = Paths.get("uploads");

    // Upload product image
    @PostMapping("/image/upload/{id}")
    public ResponseEntity<String> uploadProductImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        try {
            if (!Files.exists(uploadDirectory)) {
                Files.createDirectories(uploadDirectory);
            }

            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path targetPath = uploadDirectory.resolve(filename).normalize();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
            }

            Optional<Product> productOpt = productRepository.findById(id);
            if (productOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Product product = productOpt.get();
            product.setImageName(filename); // save the filename
            productRepository.save(product);

            return ResponseEntity.ok("Image uploaded successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload image: " + e.getMessage());
        }
    }

    // Serve product image
    @GetMapping("/image/{id}")
    @ResponseBody
    public ResponseEntity<Resource> serveProductImage(@PathVariable Long id) {
        try {
            Optional<Product> productOpt = productRepository.findById(id);
            if (productOpt.isEmpty() || productOpt.get().getImageName() == null) {
                return ResponseEntity.notFound().build();
            }

            String filename = productOpt.get().getImageName();
            Path file = uploadDirectory.resolve(filename).normalize();
            Resource resource = new UrlResource(file.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(file))
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


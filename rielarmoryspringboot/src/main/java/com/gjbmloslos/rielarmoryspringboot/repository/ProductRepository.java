package com.gjbmloslos.rielarmoryspringboot.repository;

import com.gjbmloslos.rielarmoryspringboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Fetch all products with all associations to avoid lazy-loading / concurrent modification issues
    @Query("SELECT DISTINCT p FROM Product p " +
            "LEFT JOIN FETCH p.tags " +
            "LEFT JOIN FETCH p.manufacturer " +
            "LEFT JOIN FETCH p.category " +
            "LEFT JOIN FETCH p.caliber")
    List<Product> findAllWithAssociations();

    // Fetch a single product by ID with all associations
    @Query("SELECT p FROM Product p " +
            "LEFT JOIN FETCH p.tags " +
            "LEFT JOIN FETCH p.manufacturer " +
            "LEFT JOIN FETCH p.category " +
            "LEFT JOIN FETCH p.caliber " +
            "WHERE p.productId = :id")
    Optional<Product> findByIdWithAssociations(@Param("id") Long id);

}

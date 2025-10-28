package com.gjbmloslos.rielarmoryspringboot.repository;

import com.gjbmloslos.rielarmoryspringboot.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryManufacturer extends JpaRepository<Category, Long> {
}

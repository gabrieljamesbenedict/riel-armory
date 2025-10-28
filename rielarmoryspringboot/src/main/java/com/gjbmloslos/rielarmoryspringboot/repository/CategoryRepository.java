package com.gjbmloslos.rielarmoryspringboot.repository;

import com.gjbmloslos.rielarmoryspringboot.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

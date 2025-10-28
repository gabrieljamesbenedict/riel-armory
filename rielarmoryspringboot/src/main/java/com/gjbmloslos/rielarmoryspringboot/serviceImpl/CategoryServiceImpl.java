package com.gjbmloslos.rielarmoryspringboot.serviceImpl;

import com.gjbmloslos.rielarmoryspringboot.model.Category;
import com.gjbmloslos.rielarmoryspringboot.repository.CategoryRepository;
import com.gjbmloslos.rielarmoryspringboot.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

}

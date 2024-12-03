package com.bookstore.service.impl;

import com.bookstore.entity.Category;
import com.bookstore.repository.CategoryRepository;
import com.bookstore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category saveCategory(Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(category.getId());
        if(existingCategory.isEmpty()){
            Category newCategory = new Category();
            newCategory.setId(UUID.randomUUID());
            if(category.getName()!=null) newCategory.setName(category.getName());
            return categoryRepository.save(newCategory);
        }
        Category result = existingCategory.get();
        if(category.getName()!=null) result.setName(category.getName());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
}

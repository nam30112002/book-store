package com.bookstore.service;

import com.bookstore.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(UUID id);
    Category saveCategory(Category category);
    void deleteCategory(UUID id);
}

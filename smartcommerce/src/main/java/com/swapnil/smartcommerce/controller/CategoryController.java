package com.swapnil.smartcommerce.controller;

import com.swapnil.smartcommerce.entity.Category;
import com.swapnil.smartcommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category createCategory(
            @RequestBody Category category) {

        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(
            @PathVariable Long id) {

        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);
    }
}
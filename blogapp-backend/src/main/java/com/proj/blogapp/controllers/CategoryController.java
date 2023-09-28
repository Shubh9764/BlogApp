package com.proj.blogapp.controllers;

import com.proj.blogapp.dto.CategoryDto;
import com.proj.blogapp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto newDto = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(newDto, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategoryById(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId) {
        CategoryDto updatedCategoryDto = categoryService.updateCategory(categoryDto, categoryId);

        return new ResponseEntity<>(updatedCategoryDto, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer categoryId) {
        CategoryDto updatedCategoryDto = categoryService.getCategoryById(categoryId);

        return new ResponseEntity<>(updatedCategoryDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        List<CategoryDto> updatedCategoryDto = categoryService.getAllCategories();

        return new ResponseEntity<>(updatedCategoryDto, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return new ResponseEntity<>("category with id "+categoryId+" deleted successfully", HttpStatus.OK);
    }



}

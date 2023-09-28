package com.proj.blogapp.service;

import com.proj.blogapp.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    CategoryDto getCategoryById(Integer categoryId);

    void deleteCategoryById(Integer categoryId);

    List<CategoryDto> getAllCategories();

}

package com.proj.blogapp.service.impl;

import com.proj.blogapp.dto.CategoryDto;
import com.proj.blogapp.entities.Category;
import com.proj.blogapp.exceptions.ResourceNotFoundException;
import com.proj.blogapp.repo.CategoryRepo;
import com.proj.blogapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category newCategory = categoryRepo.save(mapper.map(categoryDto,Category.class));
        return mapper.map(newCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","id",categoryId));

        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category = categoryRepo.save(category);
        return mapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","id",categoryId));
        return mapper.map(category,CategoryDto.class);
    }

    @Override
    public void deleteCategoryById(Integer categoryId) {
        categoryRepo.deleteById(categoryId);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepo.findAll().stream().map(category -> mapper.map(category,CategoryDto.class)).toList();
    }
}

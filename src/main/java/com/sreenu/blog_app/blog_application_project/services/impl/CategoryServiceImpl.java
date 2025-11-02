package com.sreenu.blog_app.blog_application_project.services.impl;

import com.sreenu.blog_app.blog_application_project.entities.Category;
import com.sreenu.blog_app.blog_application_project.entities.User;
import com.sreenu.blog_app.blog_application_project.exceptions.ResourceNotFoundException;
import com.sreenu.blog_app.blog_application_project.payloads.CategoryDto;
import com.sreenu.blog_app.blog_application_project.payloads.UserDto;
import com.sreenu.blog_app.blog_application_project.repositories.CategoryRepo;
import com.sreenu.blog_app.blog_application_project.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = modelMapper.map(categoryDto, Category.class);
        Category addedCat = categoryRepo.save(cat);
        return modelMapper.map(addedCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","Category Id", categoryId));

        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCat = categoryRepo.save(cat);
        return modelMapper.map(updatedCat,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {

         Category cat = categoryRepo.findById(categoryId)
                 .orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId));

         categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id",categoryId));
        return modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> cats = categoryRepo.findAll();

        List<CategoryDto> catdtos = new ArrayList<>();

        for(Category cat:cats)
        {
            catdtos.add(modelMapper.map(cat,CategoryDto.class));
        }

        return catdtos;
    }
}

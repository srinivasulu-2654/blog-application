package com.sreenu.blog_app.blog_application_project.services;

import com.sreenu.blog_app.blog_application_project.payloads.CategoryDto;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public interface CategoryService {

    // create

    CategoryDto createCategory(CategoryDto categoryDto);

    // update

    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    // delete

     void deleteCategory(Integer categoryId);

    // get single user

     CategoryDto getCategory(Integer categoryId);

    // get all

    List<CategoryDto> getAllCategories();
}

package com.sreenu.blog_app.blog_application_project.controllers;

import com.sreenu.blog_app.blog_application_project.payloads.ApiResponse;
import com.sreenu.blog_app.blog_application_project.payloads.CategoryDto;
import com.sreenu.blog_app.blog_application_project.repositories.CategoryRepo;
import com.sreenu.blog_app.blog_application_project.services.CategoryService;
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
    private CategoryService categoryService;

    // create

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
    {
         CategoryDto createCategory = categoryService.createCategory(categoryDto);
         return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }

    // update

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId)
    {
        CategoryDto updateCategory = categoryService.updateCategory(categoryDto,categoryId);
//        return  ResponseEntity.ok(updateCategory);
        return new  ResponseEntity<>(updateCategory,HttpStatus.OK);
    }

    // delete

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId)
    {
        categoryService.deleteCategory(categoryId);
        return  new ResponseEntity<>(new ApiResponse("Category is deleted successfully",true),HttpStatus.OK);
    }

    // get

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId)
    {
        CategoryDto getCatgeory = categoryService.getCategory(categoryId);
        return new ResponseEntity<>(getCatgeory,HttpStatus.OK);
    }

    // get all
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories()
    {
        List<CategoryDto> getAllCats = categoryService.getAllCategories();
        return new ResponseEntity<>(getAllCats,HttpStatus.OK);
    }
}

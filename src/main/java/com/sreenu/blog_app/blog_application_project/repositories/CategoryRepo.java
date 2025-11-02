package com.sreenu.blog_app.blog_application_project.repositories;

import com.sreenu.blog_app.blog_application_project.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}

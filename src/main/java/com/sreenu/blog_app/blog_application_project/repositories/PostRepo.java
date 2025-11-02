package com.sreenu.blog_app.blog_application_project.repositories;

import com.sreenu.blog_app.blog_application_project.entities.Category;
import com.sreenu.blog_app.blog_application_project.entities.Post;
import com.sreenu.blog_app.blog_application_project.entities.User;
import com.sreenu.blog_app.blog_application_project.payloads.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
}

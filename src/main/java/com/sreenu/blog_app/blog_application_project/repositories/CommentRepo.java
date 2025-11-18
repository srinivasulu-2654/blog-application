package com.sreenu.blog_app.blog_application_project.repositories;

import com.sreenu.blog_app.blog_application_project.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comments,Integer> {
}

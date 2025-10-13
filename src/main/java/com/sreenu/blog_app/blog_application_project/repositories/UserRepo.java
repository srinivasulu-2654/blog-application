package com.sreenu.blog_app.blog_application_project.repositories;

import com.sreenu.blog_app.blog_application_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

}

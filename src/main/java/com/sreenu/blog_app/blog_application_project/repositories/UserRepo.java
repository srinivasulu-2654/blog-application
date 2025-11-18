package com.sreenu.blog_app.blog_application_project.repositories;

import com.sreenu.blog_app.blog_application_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String mail);

}

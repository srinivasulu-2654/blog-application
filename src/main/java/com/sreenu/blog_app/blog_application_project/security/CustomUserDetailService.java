package com.sreenu.blog_app.blog_application_project.security;

import com.sreenu.blog_app.blog_application_project.entities.User;
import com.sreenu.blog_app.blog_application_project.exceptions.ResourceNotFoundException;
import com.sreenu.blog_app.blog_application_project.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //loading user from database by username

        // so in this username is taking as the mail itself from the User table
     User user =    userRepo.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("User","email: "+username,0)); // here we passed 0 coz we handled the ResourceNotFoundException like this only so that's why

        return user;
    }
}

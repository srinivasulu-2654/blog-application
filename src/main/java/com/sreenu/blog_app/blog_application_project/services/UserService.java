package com.sreenu.blog_app.blog_application_project.services;

import com.sreenu.blog_app.blog_application_project.entities.User;
import com.sreenu.blog_app.blog_application_project.payloads.UserDto;

import java.util.List;

public interface UserService {

   UserDto registerNewUser(UserDto userDto);

   UserDto createUser(UserDto user);
   UserDto updateUser(UserDto user,Integer userId);
   UserDto getUserById(Integer userId);
   List<UserDto> getAllUsers();
   void deleteUser(Integer userId);
}

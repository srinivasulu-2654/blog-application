package com.sreenu.blog_app.blog_application_project.controllers;

import com.sreenu.blog_app.blog_application_project.payloads.ApiResponse;
import com.sreenu.blog_app.blog_application_project.payloads.UserDto;
import com.sreenu.blog_app.blog_application_project.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST - Create user

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto createUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    // PUT  - Update user

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
       UserDto updatedUser =  userService.updateUser(userDto,userId);
       return ResponseEntity.ok(updatedUser);
    }

    // DELETE - delete user
    // ADMIN -> delete can do only admin
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
        userService.deleteUser(uid);
//        return new ResponseEntity<>(Map.of("message","User Deleted Successfully"), HttpStatus.OK);
//        So instead of passing or returning this we can return from the other class called "ApiResponse"

        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    }

    // GET  - user get

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // GET - get single user

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

}

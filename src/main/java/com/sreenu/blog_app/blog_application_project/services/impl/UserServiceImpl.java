package com.sreenu.blog_app.blog_application_project.services.impl;

import com.sreenu.blog_app.blog_application_project.entities.User;
import com.sreenu.blog_app.blog_application_project.exceptions.ResourceNotFoundException;
import com.sreenu.blog_app.blog_application_project.payloads.UserDto;
import com.sreenu.blog_app.blog_application_project.repositories.UserRepo;
import com.sreenu.blog_app.blog_application_project.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
       User user = this.dtoToUser(userDto);
       User savedUser = this.userRepo.save(user);
       return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = userRepo.save(user);
        UserDto userDto1 =  userToDto(updatedUser);


        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id",userId));

        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();

//        List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());

        List<UserDto> userDtos = new ArrayList<>();

        for(User user : users)
        {
            userDtos.add(userToDto(user));
        }

        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id",userId));
        userRepo.delete(user);
    }

    /* public User dtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());

        return userDto;
    } (these things are we converted manullay but now no neeed of manually convertion) */

    public User dtoToUser(UserDto userDto) {
        User user = modelMapper.map(userDto,User.class);
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = modelMapper.map(user,UserDto.class);

        return userDto;
    }
}

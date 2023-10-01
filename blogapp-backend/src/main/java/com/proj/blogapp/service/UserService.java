package com.proj.blogapp.service;

import com.proj.blogapp.dto.UserDto;

import java.util.List;


public interface UserService {

    UserDto registerUser(UserDto user);

    UserDto createUser(UserDto user);
    UserDto updateuser(UserDto user, Integer userId);

    UserDto getUserById(int userId);

    List<UserDto> getAllUsers();

    void deleteById(int userId);
}

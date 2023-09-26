package com.proj.blogapp.service;

import com.proj.blogapp.UserDto;

import java.util.List;


public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateuser(UserDto user,Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();
}

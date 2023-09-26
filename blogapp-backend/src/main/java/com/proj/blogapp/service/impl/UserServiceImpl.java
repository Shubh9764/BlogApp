package com.proj.blogapp.service.impl;

import com.proj.blogapp.UserDto;
import com.proj.blogapp.entities.User;
import com.proj.blogapp.repo.UserRepo;
import com.proj.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto user) {

        User user1 = userRepo.save(dtoToUser(user));

        return userToDto(user1);
    }

    @Override
    public UserDto updateuser(UserDto user, Integer userId) {
        return null;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }
    private User dtoToUser(UserDto user){
        return new User(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getAbout());
    }

    private UserDto userToDto(User user){
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getAbout());
    }
}

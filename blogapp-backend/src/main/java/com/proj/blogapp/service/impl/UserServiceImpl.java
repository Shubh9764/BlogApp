package com.proj.blogapp.service.impl;


import com.proj.blogapp.constants.AppConstants;
import com.proj.blogapp.dto.UserDto;
import com.proj.blogapp.entities.Role;
import com.proj.blogapp.entities.User;
import com.proj.blogapp.exceptions.ResourceNotFoundException;
import com.proj.blogapp.repo.RoleRepo;
import com.proj.blogapp.repo.UserRepo;
import com.proj.blogapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper mapper;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RoleRepo roleRepo;


    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = mapper.map(userDto,User.class);
        user.setPassword(encoder.encode(user.getPassword()));
        Role role = roleRepo.findById(AppConstants.ROLE_USER).orElseThrow(() -> new ResourceNotFoundException("Role","roleId",AppConstants.ROLE_USER));
        user.getRoles().add(role);
        user = userRepo.save(user);
        return mapper.map(user,UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto user) {

        User user1 = userRepo.save(mapper.map(user,User.class));

        return mapper.map(user1,UserDto.class);
    }

    @Override
    public UserDto updateuser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User"," Id ",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
       User updatedUser = userRepo.save(user);
        return mapper.map(updatedUser,UserDto.class);
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User"," Id ",userId));

        return mapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepo.findAll().stream().map(user -> mapper.map(user,UserDto.class)).toList();
    }

    @Override
    public void deleteById(int userId) {
        userRepo.deleteById(userId);
    }
    
}

package com.proj.blogapp.controllers;

import com.proj.blogapp.dto.UserDto;
import com.proj.blogapp.entities.User;
import com.proj.blogapp.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto newUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int userId) {
        UserDto newUserDto = userService.getUserById(userId);
        return new ResponseEntity<>(newUserDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateById(@PathVariable int userId, @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateuser(userDto, userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteById(@PathVariable int userId) {
        userService.deleteById(userId);
        return ResponseEntity.ok(Map.of("message", "user with userId " + userId + " delted successfully"));
    }

}

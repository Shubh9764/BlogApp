package com.proj.blogapp.controllers;

import com.proj.blogapp.dto.UserDto;
import com.proj.blogapp.jwt.JwtAuthRequest;
import com.proj.blogapp.jwt.JwtAuthResponse;
import com.proj.blogapp.jwt.JwtTokenHelper;
import com.proj.blogapp.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper ;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    ModelMapper mapper;

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login( @RequestBody JwtAuthRequest request){
         authenticate(request.getEmail(),request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        System.out.println(userDetails);
        String token = jwtTokenHelper.generateToken(userDetails);
        UserDto user = mapper.map(userDetails,UserDto.class);
        JwtAuthResponse response = new JwtAuthResponse(token,user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register( @RequestBody @Valid UserDto dto){
        UserDto registerdDto = userService.registerUser(dto);
        return new ResponseEntity<>(registerdDto,HttpStatus.CREATED);
    }
    private void authenticate(String username, String password){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);
        authenticationManager.authenticate(token);
    }

}

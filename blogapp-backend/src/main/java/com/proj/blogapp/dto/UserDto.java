package com.proj.blogapp.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private  int id;

    @Column(nullable = false, length = 100)
    @NotEmpty
    @Size(min = 2, message = "Username must be min of 2 chararcters")
    private String name;

    @Column(unique = true)
    @Email(message = "email address not valid")
    private  String email;

    @NotEmpty
    @Size(min = 3, max = 10, message = "Password must be min of 3 chars and max 10 chars !!")
    private String password;

    @NotEmpty
    private String about;
}

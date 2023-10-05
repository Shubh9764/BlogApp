package com.proj.blogapp.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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
    @NotEmpty
    private  String email;

    @NotEmpty
    @Size(min = 3, max = 10, message = "Password must be min of 3 chars and max 10 chars !!")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}

package com.proj.blogapp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer categoryId;

    @NotEmpty
    @Size(min=4,message = "Category Title must have more than 4 chars")
    private  String categoryTitle;


    @NotEmpty
    @Size(min = 10,message = "Category Description must have more than 10 chars")
    private String categoryDescription;
}

package com.example.springsecuritymodel.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Code can't be empty")
    @Size(min=2,max = 5, message = "Code must have length of 2 - 5 characters")
    @Pattern(regexp = "[A-Za-z]*",message = "Code contains illegal characters")
    private String code;

    @NotBlank(message = "Name can't be empty")
    @Pattern(regexp = "[A-Za-z]*",message = "Name contains illegal characters")
    private String name;

    @NotBlank(message = "Country can't be empty")
    @Pattern(regexp = "[A-Za-z]*",message = "Country contains illegal characters")
    private String country;
    public Department() {

    }
}

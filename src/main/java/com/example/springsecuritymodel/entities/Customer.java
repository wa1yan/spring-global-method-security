package com.example.springsecuritymodel.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="Code cannot be empty")
    @Size(min=2,message="Code must have length of 2-5 characters")
    @Pattern(regexp = "[A-Za-z]*",message = "Code contains illegal characters")
    private String code;

    @NotBlank(message="First name cannot be empty")
    @Pattern(regexp = "[A-Za-z]*",message = "First name contains illegal characters")
    @Column(name="first_name")
    private String firstName;

    @NotBlank(message="Last name cannot be empty")
    @Pattern(regexp = "[A-Za-z]*",message = "Last name contains illegal characters")
    @Column(name="last_name")
    private String lastName;

    @NotBlank(message="Address cannot be empty")
    @Pattern(regexp = "[\\w .\\-/,]*",message = "Address contains illegal characters")
    private String address;

    public Customer(){

    }
}

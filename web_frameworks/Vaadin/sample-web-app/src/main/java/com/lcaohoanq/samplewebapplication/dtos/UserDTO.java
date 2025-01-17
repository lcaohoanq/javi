package com.lcaohoanq.samplewebapplication.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(

    /*
    * private String username;
    private String password;
    private String email;
    private String role;
    private String status;*/

    @NotBlank(message = "Username is required")
    @JsonProperty("user_name")
    String username,

    @NotBlank(message = "Password is required")
    @JsonProperty("password")
    String password,
    String email,
    String role,
    String status

) {}

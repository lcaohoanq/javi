package com.lcaohoanq.demo.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserDTO(
    @JsonProperty("email")
    @NotEmpty(message = "Email is required")
    String email,

    @JsonProperty("login")
    @NotEmpty(message = "Username is required")
    @Size(min = 6, max = 20)
    String login,

    @JsonProperty("password")
    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 20)
    String password
) {

}

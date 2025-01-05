package com.lcaohoanq.demo.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @JsonProperty("username")
    @NotEmpty(message = "Username is required")
    @Size(min = 6, max = 20)
    private String username;

    @JsonProperty("password")
    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 20)
    private String password;

}

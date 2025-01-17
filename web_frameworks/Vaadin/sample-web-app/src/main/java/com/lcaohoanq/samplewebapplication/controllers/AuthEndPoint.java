package com.lcaohoanq.samplewebapplication.controllers;

import com.lcaohoanq.samplewebapplication.dtos.UserDTO;
import com.lcaohoanq.samplewebapplication.models.User;
import com.lcaohoanq.samplewebapplication.services.IUserService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.Endpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@Endpoint
@AnonymousAllowed
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/auth")
public class AuthEndPoint {

  private final IUserService userService;

  public User login(String username, String password) {
    return userService.login(username, password);
  }

  public User register(UserDTO userDTO) {
    return userService.register(userDTO);
  }

}

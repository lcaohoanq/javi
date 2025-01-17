package com.lcaohoanq.samplewebapplication.controllers;

import com.lcaohoanq.samplewebapplication.services.IUserService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.Endpoint;
import com.vaadin.hilla.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lcaohoanq.samplewebapplication.models.User;

@Endpoint
@AnonymousAllowed
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserEndPoint {

  private final IUserService userService;

  @GetMapping("/{id}")
  public User findById(
    @PathVariable @Nonnull Long id) {
    return userService.findById(id);
  }

}

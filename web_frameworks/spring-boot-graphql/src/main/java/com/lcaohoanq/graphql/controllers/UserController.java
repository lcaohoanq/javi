package com.lcaohoanq.graphql.controllers;

import com.lcaohoanq.graphql.Entity.User;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @QueryMapping(name = "getAllUsers")
    public List<User> getAll() {
        return User.users;
    }

    @QueryMapping
    public User getById(@Argument Integer userId) {
        return User.users.stream().filter(user -> user.id() == userId).findFirst().orElseThrow();
    }


}

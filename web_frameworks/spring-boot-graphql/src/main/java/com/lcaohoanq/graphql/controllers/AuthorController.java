package com.lcaohoanq.graphql.controllers;

import com.lcaohoanq.graphql.Entity.Author;
import java.util.List;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorController {

    @QueryMapping
    public List<Author> authors() {
        return Author.authors;
    }
    
    @QueryMapping
    public Author authorById(Integer id) {
        return Author.getAuthorById(id).orElse(null);
    }
    
}

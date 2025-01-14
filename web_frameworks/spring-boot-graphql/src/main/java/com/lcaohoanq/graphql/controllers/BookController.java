package com.lcaohoanq.graphql.controllers;

import com.lcaohoanq.graphql.exception.BookNotFoundException;
import com.lcaohoanq.graphql.Entity.Author;
import com.lcaohoanq.graphql.Entity.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    @QueryMapping(name = "books")
    public List<Book> getAllBooks() {
        return Book.books;
    }

    @QueryMapping
    public Book bookById(@Argument Integer id) {
        return Book.getBookById(id)
            .orElseThrow(() -> new BookNotFoundException(id));
    }

    @SchemaMapping
    public Optional<Author> author(Book book) {
        return Author.getAuthorById(book.authorId());
    }
    
}

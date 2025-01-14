package com.lcaohoanq.graphql.controllers;

import com.lcaohoanq.graphql.Entity.Author;
import com.lcaohoanq.graphql.Entity.Book;
import com.lcaohoanq.graphql.Entity.Publisher;
import java.util.List;
import java.util.Optional;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PublisherController {

    @QueryMapping
    public List<Publisher> publishers() {
        return Publisher.publishers;
    }

    @QueryMapping
    public Optional<Publisher> publisherById(@Argument Integer id) {
        return Publisher.getPublisherById(id);
    }

    // SchemaMapping to resolve the author for a publisher
    @SchemaMapping
    public Optional<Author> author(Publisher publisher) {
        return Author.getAuthorById(publisher.authorId());
    }

    // SchemaMapping to resolve the book for a publisher
    @SchemaMapping
    public Optional<Book> book(Publisher publisher) {
        return Book.getBookById(publisher.bookId());
    }
}

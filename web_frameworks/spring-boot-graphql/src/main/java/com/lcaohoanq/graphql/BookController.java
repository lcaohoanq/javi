package com.lcaohoanq.graphql;

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

    @QueryMapping
    public List<Book> books() {
        return Book.books;
    }

    @QueryMapping
    public Optional<Book> bookById(@Argument Integer id) {
        return Book.getBookById(id);
    }

    @SchemaMapping
    public Optional<Author> author(Book book) {
        return Author.getAuthorById(book.authorId());
    }
}

package com.lcaohoanq.graphql.controllers;

import com.lcaohoanq.graphql.Entity.Author;
import com.lcaohoanq.graphql.Entity.Book;
import com.lcaohoanq.graphql.dto.PageInput;
import com.lcaohoanq.graphql.exception.BookNotFoundException;
import graphql.relay.PageInfo;
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

    @QueryMapping(name = "paginatedBooks")
    public BookConnection getPaginatedBooks(@Argument PageInput pageInput) {
        int totalBooks = Book.books.size();
        int totalPages = (int) Math.ceil((double) totalBooks / pageInput.size());

        List<Book> paginatedList = Book.books.stream()
            .skip(pageInput.getOffset())
            .limit(pageInput.size())
            .toList();

        PageInfo pageInfo = new PageInfo(
            pageInput.page() < totalPages,
            pageInput.page() > 1,
            pageInput.page(),
            totalPages
        );

        return new BookConnection(paginatedList, pageInfo, totalBooks);
    }

    // Cursor-based pagination
    @QueryMapping(name = "paginatedBooksWithCursor")
    public BookConnection getPaginatedBooksWithCursor(
        @Argument Integer first,
        @Argument String after
    ) {
        int cursor = after != null ? Integer.parseInt(after) : 0;

        List<Book> paginatedList = Book.books.stream()
            .skip(cursor)
            .limit(first)
            .toList();

        boolean hasNextPage = (cursor + first) < Book.books.size();
        boolean hasPreviousPage = cursor > 0;

        PageInfo pageInfo = new PageInfo(
            hasNextPage,
            hasPreviousPage,
            cursor / first + 1,
            (int) Math.ceil((double) Book.books.size() / first)
        );

        return new BookConnection(paginatedList, pageInfo, Book.books.size());
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

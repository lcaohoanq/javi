package com.lcaohoanq.graphql.exception;

public class BookNotFoundException extends DataNotFoundException {

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(Integer id) {
        super("Book with id " + id + " not found.");
    }
}

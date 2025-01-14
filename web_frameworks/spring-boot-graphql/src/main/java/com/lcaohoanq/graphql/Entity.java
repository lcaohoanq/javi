package com.lcaohoanq.graphql;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface Entity {

    record Author(Integer id,
                  String name) {
        public static List<Author> authors = Arrays.asList(
            new Author(1, "Mama Samba"),
            new Author(2, "Jamila"),
            new Author(3, "Allah")

        );

        public static Optional<Author> getAuthorById(Integer id) {
            return authors.stream()
                .filter(b -> b.id.equals(id))
                .findFirst();
        }
    }

    record Book(Integer id,
                String name,
                Integer pageCount,
                Integer authorId) {
        public static List<Book> books = Arrays.asList(
            new Book(1, "Quran", 604, 3),
            new Book(2, "Harry Potter", 700, 2),
            new Book(3, "Foobar", 100, 1),
            new Book(4, "Spring Boot", 344, 2)
        );

        public static Optional<Book> getBookById(Integer id) {
            return books.stream()
                .filter(b -> b.id.equals(id))
                .findFirst();
        }
    }
    
}

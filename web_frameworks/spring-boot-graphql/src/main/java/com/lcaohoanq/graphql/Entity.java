package com.lcaohoanq.graphql;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface Entity {

    record User(Integer id, String name, String email, String password) {
        public static List<User> users = Arrays.asList(
            new User(1, "Mama Samba", "mamma@example.com", "1"),
            new User(2, "Jamila", "jamila@gmail.com", "1")
        );
    }

    record Publisher(Integer id, String name, Integer authorId, Integer bookId) {

        public static List<Publisher> publishers = Arrays.asList(
            new Publisher(1, "Mama Samba", 1, 1),
            new Publisher(2, "Jamila", 2, 2),
            new Publisher(3, "Allah", 3, 3)
        );

        public static Optional<Publisher> getPublisherById(Integer id) {
            return publishers.stream()
                .filter(b -> b.id.equals(id))
                .findFirst();
        }
    }

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

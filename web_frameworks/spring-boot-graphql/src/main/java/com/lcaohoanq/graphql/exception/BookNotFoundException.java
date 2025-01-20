package com.lcaohoanq.graphql.exception;

import java.util.HashMap;
import java.util.Map;

public class BookNotFoundException extends DataNotFoundException {

    public BookNotFoundException(Integer id) {
        super(buildMessage(id), buildDetails(id));
    }

    private static String buildMessage(Integer id) {
        return String.format("Book with ID %d could not be found in the system", id);
    }

    private static Map<String, Object> buildDetails(Integer id) {
        Map<String, Object> details = new HashMap<>();
        details.put("resourceType", "Book");
        details.put("resourceId", id);
        details.put("suggestion", "Please verify the book ID and try again");
        return details;
    }
}

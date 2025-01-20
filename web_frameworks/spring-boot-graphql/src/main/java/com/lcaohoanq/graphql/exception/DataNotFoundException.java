package com.lcaohoanq.graphql.exception;

import java.util.Map;

public class DataNotFoundException extends RuntimeException {

    private Map<String, Object> details;

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Map<String, Object> details) {
        super(message);
        this.details = details;
    }

    public Map<String, Object> getDetails() {
        return details;
    }
}

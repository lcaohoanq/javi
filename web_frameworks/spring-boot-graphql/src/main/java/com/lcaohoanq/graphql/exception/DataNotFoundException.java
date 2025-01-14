package com.lcaohoanq.graphql.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }
    
    public DataNotFoundException(Integer id){
        super("Notfound: " + id);
    }
}

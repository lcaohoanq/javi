package com.lcaohoanq.samplewebapplication.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {

    public EmailAlreadyUsedException(String message) {
        super(message);
    }
}

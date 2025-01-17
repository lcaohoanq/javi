package com.lcaohoanq.samplewebapplication.exceptions;

public class UserHasBeenVerifiedException extends RuntimeException {

    public UserHasBeenVerifiedException(String message) {
        super(message);
    }
}

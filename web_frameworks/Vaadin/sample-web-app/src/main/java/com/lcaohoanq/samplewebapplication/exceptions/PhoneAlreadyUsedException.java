package com.lcaohoanq.samplewebapplication.exceptions;

public class PhoneAlreadyUsedException extends RuntimeException {

    public PhoneAlreadyUsedException(String message) {
        super(message);
    }
}

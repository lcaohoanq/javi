package com.lcaohoanq.samplewebapplication.exceptions;

import com.lcaohoanq.samplewebapplication.exceptions.base.DataNotFoundException;

public class TokenNotFoundException extends DataNotFoundException {

    public TokenNotFoundException(String message) {
        super(message);
    }

}

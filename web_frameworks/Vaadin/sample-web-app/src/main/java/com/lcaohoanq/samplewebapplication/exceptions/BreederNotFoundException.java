package com.lcaohoanq.samplewebapplication.exceptions;

import com.lcaohoanq.samplewebapplication.exceptions.base.DataNotFoundException;

public class BreederNotFoundException extends DataNotFoundException {

    public BreederNotFoundException(String message) {
        super(message);
    }

}

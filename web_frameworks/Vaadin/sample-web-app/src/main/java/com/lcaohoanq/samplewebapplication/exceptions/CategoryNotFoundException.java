package com.lcaohoanq.samplewebapplication.exceptions;

import com.lcaohoanq.samplewebapplication.exceptions.base.DataNotFoundException;

public class CategoryNotFoundException extends DataNotFoundException {

    public CategoryNotFoundException(String message) {
        super(message);
    }

}

package com.lcaohoanq.samplewebapplication.exceptions;

import com.lcaohoanq.samplewebapplication.exceptions.base.DataAlreadyExistException;

public class CategoryAlreadyExistException extends DataAlreadyExistException {

    public CategoryAlreadyExistException(String message) {
        super(message);
    }

}

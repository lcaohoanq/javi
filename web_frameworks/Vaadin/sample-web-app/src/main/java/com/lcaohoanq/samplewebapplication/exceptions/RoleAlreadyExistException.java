package com.lcaohoanq.samplewebapplication.exceptions;

import com.lcaohoanq.samplewebapplication.exceptions.base.DataAlreadyExistException;

public class RoleAlreadyExistException extends DataAlreadyExistException {

    public RoleAlreadyExistException(String message) {
        super(message);
    }

}

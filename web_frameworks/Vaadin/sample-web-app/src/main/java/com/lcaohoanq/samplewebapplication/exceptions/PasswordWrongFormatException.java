package com.lcaohoanq.samplewebapplication.exceptions;

import com.lcaohoanq.samplewebapplication.exceptions.base.DataWrongFormatException;

public class PasswordWrongFormatException extends DataWrongFormatException {

    public PasswordWrongFormatException(String message) {
        super(message);
    }

}

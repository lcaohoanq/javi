package com.lcaohoanq.samplewebapplication.exceptions.base;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DataAlreadyExistException extends RuntimeException{

    public DataAlreadyExistException(String message) {
        super(message);
    }

}

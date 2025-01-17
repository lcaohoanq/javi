package com.lcaohoanq.samplewebapplication.exceptions;

import com.lcaohoanq.samplewebapplication.exceptions.base.DataNotFoundException;

public class StaffNotFoundException extends DataNotFoundException {

    public StaffNotFoundException(String message) {
        super(message);
    }

}

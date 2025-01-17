package com.lcaohoanq.samplewebapplication.exceptions;

import com.lcaohoanq.samplewebapplication.exceptions.base.DataNotFoundException;

public class RoleNotFoundException extends DataNotFoundException {

    public RoleNotFoundException(String message) {
        super(message);
    }

}

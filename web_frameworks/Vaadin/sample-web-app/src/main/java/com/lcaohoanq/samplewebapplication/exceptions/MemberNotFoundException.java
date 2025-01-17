package com.lcaohoanq.samplewebapplication.exceptions;

import com.lcaohoanq.samplewebapplication.exceptions.base.DataNotFoundException;

public class MemberNotFoundException extends DataNotFoundException {

    public MemberNotFoundException(String message) {
        super(message);
    }

}

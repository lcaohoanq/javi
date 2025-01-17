package com.lcaohoanq.samplewebapplication.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    MEMBER("MEMBER"),
    STAFF("STAFF"),
    MANAGER("MANAGER");

    private final String role;

}

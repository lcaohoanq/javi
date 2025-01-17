package com.lcaohoanq.samplewebapplication.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    UNVERIFIED("UNVERIFIED"),
    VERIFIED("VERIFIED"),
    BANNED("BANNED");

    private final String status;

}

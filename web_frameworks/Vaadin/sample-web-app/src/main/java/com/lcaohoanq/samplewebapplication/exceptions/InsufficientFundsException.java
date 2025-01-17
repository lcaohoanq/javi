package com.lcaohoanq.samplewebapplication.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }

}

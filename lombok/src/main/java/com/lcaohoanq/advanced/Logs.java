package com.lcaohoanq.advanced;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Logs {

    //https://projectlombok.org/features/log

    //instead of need to write:
    //private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Logs.class);
    public static void main(String[] args) {
        log.info("Hello, World!");
        //logs/app.log
        //2024-07-16 17:13:34 INFO  com.lcaohoanq.advanced.Logs - Hello, World!
    }

}

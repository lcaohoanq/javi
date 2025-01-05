package com.lcaohoanq.advanced.io.c_compiler.model;

import java.util.UUID;

public record Testcase(
    UUID id,
    String input,
    String output
) { }
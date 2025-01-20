package com.lcaohoanq.graphql.dto;

public record PageInput(Integer page, Integer size) {
    public int getOffset() {
        return (page - 1) * size;
    }
}

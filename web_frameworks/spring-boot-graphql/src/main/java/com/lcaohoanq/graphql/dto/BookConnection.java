package com.lcaohoanq.graphql.dto;

import com.lcaohoanq.graphql.Entity.Book;
import graphql.relay.PageInfo;
import java.util.List;

public record BookConnection(
    List<Book> nodes,
    PageInfo pageInfo,
    int totalCount
) {}
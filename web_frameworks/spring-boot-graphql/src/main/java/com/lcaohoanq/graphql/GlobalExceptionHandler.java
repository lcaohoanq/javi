package com.lcaohoanq.graphql;

import com.lcaohoanq.graphql.exception.BookNotFoundException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @GraphQlExceptionHandler
    public GraphQLError handleBookNotFoundException(BookNotFoundException ex) {
        // Customize the error response for the client
        return GraphqlErrorBuilder.newError()
            .message(ex.getMessage())
            .errorType(ErrorType.NOT_FOUND)
            .build();
    }

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public GraphQLError handleGenericException(Exception ex) {
        // Customize the error response for generic exceptions
        return GraphqlErrorBuilder.newError()
            .message("Internal Server Error: " + ex.getMessage())
            .errorType(ErrorType.INTERNAL_ERROR)
            .build();
    }
}

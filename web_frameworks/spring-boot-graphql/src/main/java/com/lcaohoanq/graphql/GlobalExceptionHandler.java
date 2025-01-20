package com.lcaohoanq.graphql;

import com.lcaohoanq.graphql.exception.BookNotFoundException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @GraphQlExceptionHandler
    public GraphQLError handleBookNotFoundException(BookNotFoundException ex, DataFetchingEnvironment env) {
        Map<String, Object> extensions = new HashMap<>();
        extensions.put("classification", "NOT_FOUND");
        extensions.put("timestamp", ZonedDateTime.now().toString());
        extensions.put("path", env.getExecutionStepInfo().getPath().toString());
        extensions.put("errorCode", "BOOK_001");

        // Add validation details if available
        if (ex.getDetails() != null) {
            extensions.put("details", ex.getDetails());
        }

        return GraphqlErrorBuilder.newError()
            .message(ex.getMessage())
            .errorType(ErrorType.NOT_FOUND)
            .extensions(extensions)
            .path(env.getExecutionStepInfo().getPath())
            .location(env.getField().getSourceLocation())
            .build();
    }

    @ExceptionHandler(Exception.class)
    public GraphQLError handleGenericException(Exception ex, DataFetchingEnvironment env) {
        Map<String, Object> extensions = new HashMap<>();
        extensions.put("classification", "INTERNAL_ERROR");
        extensions.put("timestamp", ZonedDateTime.now().toString());
        extensions.put("path", env.getExecutionStepInfo().getPath().toString());
        extensions.put("errorCode", "GEN_001");

        return GraphqlErrorBuilder.newError()
            .message("An unexpected error occurred")
            .errorType(ErrorType.INTERNAL_ERROR)
            .extensions(extensions)
            .path(env.getExecutionStepInfo().getPath())
            .location(env.getField().getSourceLocation())
            .build();
    }
}

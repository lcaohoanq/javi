package com.lcaohoanq.advanced.io.c_compiler.model;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submission {
    
    private UUID id = UUID.randomUUID();

    private Integer attempt = 0;

    private ProgrammingLanguage programmingLanguage = ProgrammingLanguage.C;

    private Double memory = 0.0;

    private Double codeSize = 0.0;

    private Double runtimeMetric = 0.0;

    private String note;

    private Integer testcasePassed = 0;

    private Double timeTaken = 0.0;

    private SubmissionStatus status;

    private Double executionTime = 0.0;
    
    private String sourceCode;

    private String compilerOutput;

    private String testCaseResults;

    @Getter
    @AllArgsConstructor
    public enum ProgrammingLanguage {
        C("C"),
        CSHARP("C#"),
        CPP("C++"),
        JAVA("Java"),
        PYTHON("Python"),
        DART("Dart"),
        JAVASCRIPT("JavaScript"),
        TYPESCRIPT("TypeScript"),
        KOTLIN("Kotlin"),
        SWIFT("Swift"),
        PHP("PHP"),
        RUBY("Ruby"),
        GO("Go"),
        RUST("Rust"),
        SCALA("Scala"),
        TCL("Tcl"),
        ELIXIR("Elixir"),
        ERLANG("Erlang"),
        RACKET("Racket");

        private String name;

        @JsonValue
        public int toValue() {
            return ordinal();
        }
    }

    public enum SubmissionStatus {
        PENDING,
        PROCESSING,
        ACCEPTED,
        WRONG_ANSWER,
        COMPILE_ERROR,
        RUNTIME_ERROR,
        TIME_LIMIT_EXCEEDED,
        MEMORY_LIMIT_EXCEEDED;

        @JsonValue
        public int toValue() {
            return ordinal();
        }
    }
    
}

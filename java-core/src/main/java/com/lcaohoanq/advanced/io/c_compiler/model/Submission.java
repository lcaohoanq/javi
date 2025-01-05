package com.lcaohoanq.advanced.io.c_compiler.model;

import java.util.UUID;

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

        ProgrammingLanguage(String racket) {
            this.name = racket;
        }

        private String getName() {
            return name;
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
    }

    public Submission(UUID id, Integer attempt, ProgrammingLanguage programmingLanguage,
                      Double memory,
                      Double codeSize, Double runtimeMetric, String note, Integer testcasePassed,
                      Double timeTaken, SubmissionStatus status, Double executionTime,
                      String sourceCode, String compilerOutput, String testCaseResults) {
        this.id = id;
        this.attempt = attempt;
        this.programmingLanguage = programmingLanguage;
        this.memory = memory;
        this.codeSize = codeSize;
        this.runtimeMetric = runtimeMetric;
        this.note = note;
        this.testcasePassed = testcasePassed;
        this.timeTaken = timeTaken;
        this.status = status;
        this.executionTime = executionTime;
        this.sourceCode = sourceCode;
        this.compilerOutput = compilerOutput;
        this.testCaseResults = testCaseResults;
    }

    public Submission() {
    }

    public UUID getId() {
        return id;
    }

    public Integer getAttempt() {
        return attempt;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public Double getMemory() {
        return memory;
    }

    public Double getCodeSize() {
        return codeSize;
    }

    public Double getRuntimeMetric() {
        return runtimeMetric;
    }

    public String getNote() {
        return note;
    }

    public Integer getTestcasePassed() {
        return testcasePassed;
    }

    public Double getTimeTaken() {
        return timeTaken;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public Double getExecutionTime() {
        return executionTime;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public String getCompilerOutput() {
        return compilerOutput;
    }

    public String getTestCaseResults() {
        return testCaseResults;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }

    public void setProgrammingLanguage(
        ProgrammingLanguage programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public void setMemory(Double memory) {
        this.memory = memory;
    }

    public void setCodeSize(Double codeSize) {
        this.codeSize = codeSize;
    }

    public void setRuntimeMetric(Double runtimeMetric) {
        this.runtimeMetric = runtimeMetric;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setTestcasePassed(Integer testcasePassed) {
        this.testcasePassed = testcasePassed;
    }

    public void setTimeTaken(Double timeTaken) {
        this.timeTaken = timeTaken;
    }

    public void setStatus(SubmissionStatus status) {
        this.status = status;
    }

    public void setExecutionTime(Double executionTime) {
        this.executionTime = executionTime;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public void setCompilerOutput(String compilerOutput) {
        this.compilerOutput = compilerOutput;
    }

    public void setTestCaseResults(String testCaseResults) {
        this.testCaseResults = testCaseResults;
    }

    @Override
    public String toString() {
        return "Submission{" +
            "id=" + id +
            ", attempt=" + attempt +
            ", programmingLanguage=" + programmingLanguage +
            ", memory=" + memory +
            ", codeSize=" + codeSize +
            ", runtimeMetric=" + runtimeMetric +
            ", note='" + note + '\'' +
            ", testcasePassed=" + testcasePassed +
            ", timeTaken=" + timeTaken +
            ", status=" + status +
            ", executionTime=" + executionTime +
            ", sourceCode='" + sourceCode + '\'' +
            ", compilerOutput='" + compilerOutput + '\'' +
            ", testCaseResults='" + testCaseResults + '\'' +
            '}';
    }
}

package com.lcaohoanq.advanced.io.c_compiler;

import com.lcaohoanq.advanced.io.c_compiler.model.Submission;
import com.lcaohoanq.advanced.io.c_compiler.model.Submission.SubmissionStatus;
import com.lcaohoanq.advanced.io.c_compiler.model.Testcase;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Sum {

    // Declare native method
    public native int sum(int a, int b);

    // Load the shared library
    static {
        System.loadLibrary("sum"); // 'sum' is the name of the shared library
    }

    // Method to run shell commands
    private static void runCommand(String[] command) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.inheritIO(); // Redirect output and error streams to console
        Process process = pb.start();
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Command failed with exit code: " + exitCode);
        }
    }

    // Compile the C code into a shared library and measure time
    private static long compileCCode() throws IOException, InterruptedException {
        String[] compileCommand = {
            "gcc", "-shared", "-fPIC",
            "-I/usr/lib/jvm/java-17-openjdk-amd64/include",
            "-I/usr/lib/jvm/java-17-openjdk-amd64/include/linux",
            "-o", "libsum.so",
            "/media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler/sum.c"
        };
        long startTime = System.currentTimeMillis();  // Start time for compilation
        runCommand(compileCommand); // Compile the C code
        long endTime = System.currentTimeMillis();    // End time for compilation
        return (endTime - startTime);                 // Return time in milliseconds
    }

    // Execute test cases and return results
    private static void executeTestCases(Submission submission, List<Testcase> testcases) {
        Sum sumObj = new Sum();

        int passed = 0;
        StringBuilder testCaseResults = new StringBuilder();

        for (Testcase testcase : testcases) {
            // Split input to get integer values
            String[] inputs = testcase.input().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);

            // Measure execution time
            long startTime = System.currentTimeMillis();
            int result = sumObj.sum(a, b); // Call the native method
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            // Check if output matches expected result
            boolean isCorrect = Integer.toString(result).equals(testcase.output());

            // Log test case result
            testCaseResults.append("Test Case ")
                .append(testcase.id()).append(": ")
                .append(isCorrect ? "PASSED" : "FAILED")
                .append(" (Execution time: ").append(executionTime).append(" ms)\n");

            // Track results
            if (isCorrect) {
                passed++;
            }
        }

        // Update submission with results
        submission.setTestcasePassed(passed);
        submission.setTestCaseResults(testCaseResults.toString());
        submission.setStatus(
            passed == testcases.size() ? SubmissionStatus.ACCEPTED : SubmissionStatus.WRONG_ANSWER);
    }

    // Main method to compile, run, and test the sum function
    public static void main(String[] args) {
        try {
            // Create a new submission
            Submission submission = new Submission();
            
            submission.setProgrammingLanguage(Submission.ProgrammingLanguage.C);
            submission.setAttempt(1);
            
            // Measure the time for compiling the C code
            System.out.println("Compiling C code...");
            long compilationTime = compileCCode();
            submission.setCompilerOutput(
                "C code compiled successfully in " + compilationTime + " ms");
            System.out.println(submission.getCompilerOutput());

            // Create test cases for the sum function
            List<Testcase> testcases = Arrays.asList(
                new Testcase(UUID.randomUUID(), "5 3", "8"),
                new Testcase(UUID.randomUUID(), "10 15", "25"),
                new Testcase(UUID.randomUUID(), "0 0", "0"),
                new Testcase(UUID.randomUUID(), "-5 10", "5"),
                new Testcase(UUID.randomUUID(), "-5 5", "0"),
                new Testcase(UUID.randomUUID(), "-1 1", "5"),
                new Testcase(UUID.randomUUID(), "-3 6", "2"),
                new Testcase(UUID.randomUUID(), "4 1", "5"),
                new Testcase(UUID.randomUUID(), "-5 10", "6"),
                new Testcase(UUID.randomUUID(), "-5 10", "7")
            );

            // Execute test cases and calculate execution time
            executeTestCases(submission, testcases);

            // Output results
            System.out.println("Test case results:");
            System.out.println(submission.getTestCaseResults());
            System.out.println(
                "Test cases passed: " + submission.getTestcasePassed() + "/" + testcases.size());
            System.out.println("Submission status: " + submission.getStatus());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.lcaohoanq.advanced.io.c_compiler;

import java.io.IOException;

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

    // Main method to compile, run, and test the sum function
    public static void main(String[] args) {
        try {
            // Measure the time for compiling the C code
            System.out.println("Compiling C code...");
            long compilationTime = compileCCode();
            System.out.println("C code compiled successfully.");
            System.out.println("Compilation time: " + compilationTime + " milliseconds");

            // Load and execute the native method
            Sum sumObj = new Sum();

            // Measure the execution time of calling the sum function
            long startTime = System.currentTimeMillis(); // Start time for execution
            int result = sumObj.sum(5, 3);               // Call the sum function
            long endTime = System.currentTimeMillis();   // End time for execution
            long executionTime = endTime - startTime;    // Calculate execution time
            // Output results
            System.out.println("Result of 5 + 3 = " + result);
            System.out.println("Execution time: " + executionTime + " milliseconds");

            // Assert the result is correct
            assert result == 8 : "Test failed!";
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
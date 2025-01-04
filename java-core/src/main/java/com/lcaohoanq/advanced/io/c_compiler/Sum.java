package com.lcaohoanq.advanced.io.c_compiler;

public class Sum {
    // Declare native method
    public native int sum(int a, int b);

    // Load the shared library
    static {
        System.loadLibrary("sum"); // 'sum' is the name of the shared library
    }

    // Main method to test the sum function
    public static void main(String[] args) {
        Sum sumObj = new Sum();
        int result = sumObj.sum(5, 3); // Test the sum function
        System.out.println("Result of 5 + 3 = " + result);
        assert result == 8 : "Test failed!"; // Assert the result is correct
    }
}

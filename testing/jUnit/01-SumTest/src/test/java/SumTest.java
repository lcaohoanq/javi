

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SumTest {

    @Test
    public void testFindSum() {
        // Test case 1: Regular array
        Sum sum = new Sum();
//        var su1 = new Sum(); Java 10
        int[] arr1 = {1, 2, 3, 4, 5};
        Assertions.assertEquals(15, sum.findSum(arr1), "Test Case 1 Failed");

        // Test case 2: Empty array
        int[] arr2 = {};
        Assertions.assertEquals(0, sum.findSum(arr2), "Test Case 2 Failed");

        // Test case 3: Array with negative numbers
        int[] arr3 = {-1, -2, -3, -4, -5};
        Assertions.assertEquals(-15, sum.findSum(arr3), "Test Case 3 Failed");

        // Add more test cases as needed
    }
}



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Sum {

    // Problem: Find the sum of elements in an array
    public static int findSum(int[] arr) {
        int sum = 0;

        // Loop through the array and add each element to the sum
        for (int num : arr) {
            sum += num;
        }

        return sum;
    }

}

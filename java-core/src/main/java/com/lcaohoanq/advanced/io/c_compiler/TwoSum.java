package com.lcaohoanq.advanced.io.c_compiler;

public class TwoSum {

    // Declare native method
    public native int[] twoSum(int[] nums, int numsSize, int target);

    // Load the shared library
    static {
        System.loadLibrary("twoSum"); // 'sum' is the name of the shared library
    }

    // Main method to test the twoSum function
    public static void main(String[] args) {
        TwoSum twoSumObj = new TwoSum();

        // Example 1: nums = [2,7,11,15], target = 9
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = twoSumObj.twoSum(nums1, nums1.length, target1);
        System.out.println("Result: [" + result1[0] + ", " + result1[1] + "]"); // Expected: [0, 1]

        // Example 2: nums = [3, 2, 4], target = 6
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = twoSumObj.twoSum(nums2, nums2.length, target2);
        System.out.println("Result: [" + result2[0] + ", " + result2[1] + "]"); // Expected: [1, 2]

        // Example 3: nums = [3, 3], target = 6
        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] result3 = twoSumObj.twoSum(nums3, nums3.length, target3);
        System.out.println("Result: [" + result3[0] + ", " + result3[1] + "]"); // Expected: [0, 1]
    }
}


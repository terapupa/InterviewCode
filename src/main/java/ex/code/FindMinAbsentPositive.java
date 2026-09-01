package ex.code;

/*
Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.

Example 1:
Input: nums = [1,2,0]
Output: 3
Explanation: The numbers in the range [1,2] are all in the array.

Example 2:

Input: nums = [3,4,-1,1]
Output: 2
Explanation: 1 is in the array but 2 is missing.

Example 3:

Input: nums = [7,8,9,11,12]
Output: 1
Explanation: The smallest positive integer 1 is missing.


Constraints:
        1 <= nums.length <= 105
        -231 <= nums[i] <= 231 - 1
*/

import java.util.Arrays;

public class FindMinAbsentPositive {
    public static int firstMissingPositive(int[] nums) {
        sort(nums);
        int smallestMissing = 1;
        for (int num : nums) {
            if (num == smallestMissing) {
                smallestMissing++; // Increment to check for the next positive integer
            }
        }
        return smallestMissing;
    }

    public static void sort(int[] arr) {
        if (arr.length == 0) return;
        int max = 0;
        int min = 0;
        for (int num : arr) {
            if (num > max) {
                max = num;
            } else if (num < min) {
                min = num;
            }
        }
        boolean[] count = new boolean[max - min + 1];
        for (int num : arr) {
            if (num > 0) {
                int index = num - min;
                count[index] = true;
            }
        }
        int arrayIndex = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i]) {
                arr[arrayIndex] = i + min;
                arrayIndex++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums3 = {7, 17, -2, 9, 13, 77, -8, 6, 8, 9, 54, 11, 19, 3, 12, 1, 2};
        System.out.println("Input: " + Arrays.toString(nums3) + " Output: " + firstMissingPositive(nums3));
    }

}

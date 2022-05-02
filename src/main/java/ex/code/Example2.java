/*
Find a subset of contiguous numbers from a given array of positive numbers that equal a sum.
    Order should be maintained. Expect an O(n) solution.

    [1, 3, 5, 4, 7]  //
    sum: 8
    expected: [3, 5]

    sum: 7
    expected: [7]

    O(n*arraySize)

    What happens if we allow negative numbers?
    */

package ex.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Example2 {

  private static Integer[] findArray(int[] input, int sum) {
    List<Integer> resultList = new ArrayList<>();
    int calculate = 0;
    for (int i : input) {
      calculate = calculate + i;
      if (calculate == sum) {
        resultList.add(i);
        return resultList.toArray(new Integer[0]);
      } else if (calculate > sum) {
        int[] inputTmp = new int[input.length - 1];
        System.arraycopy(input, 1, inputTmp, 0, input.length - 1);
        return findArray(inputTmp, sum);
      } else {
        resultList.add(i);
      }
    }
    return resultList.toArray(new Integer[0]);
  }

    public static void main (String[] args) {

      int[] arr = {1, 3, 5, 4, 7};
      int sum = 9;

      System.out.println(Arrays.toString(findArray(arr, sum)));
      System.out.println("You're running Java!");
    }
}

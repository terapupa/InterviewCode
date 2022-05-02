package invidi.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DigitalClockPermutations {

  public static void main(String[] args) {
    int[] fourDigArray = {3, 2, 7, 1, 8};
    Collections.shuffle(Arrays.asList(fourDigArray));

    printClockPermutations(fourDigArray);
  }

  private static void printClockPermutations(int[] fourDigArray) {
    List<int[]> permutations = new ArrayList<>();
    getAllPermutations(permutations, fourDigArray.length, fourDigArray);
    System.out.println(getFiltered(permutations));

  }
  public static void getAllPermutations(List<int[]> result, int n, int[] array) {
    if (n == 1) {
      result.add(array.clone());
    } else {
      for (int i = 0; i < n - 1; i++) {
        getAllPermutations(result, n - 1, array);
        if (n % 2 == 0) {
          swap(array, i, n - 1);
        } else {
          swap(array, 0, n - 1);
        }
      }
      getAllPermutations(result, n - 1, array);
    }
  }

  private static void swap(int[] input, int a, int b) {
    int tmp = input[a];
    input[a] = input[b];
    input[b] = tmp;
  }

  public static int getFiltered(List<int[]> permutations) {
    return (int) permutations.stream().filter(ints ->
        (ints[0] >= 0 && ints[0] <= 1 && ints[1] >= 0 && ints[1] <= 9 && ints[2] >= 0 && ints[2] <= 5 && ints[3] >= 0 && ints[3] <= 9) ||
            (ints[0] == 2 && ints[1] >= 0 && ints[1] <= 4 && ints[2] >= 0 && ints[2] <= 5 && ints[3] >= 0 && ints[3] <= 9)).count();
  }
}

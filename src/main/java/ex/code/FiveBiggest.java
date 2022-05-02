package ex.code;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FiveBiggest {

  private static final Integer[] inputArray = {1, 6, 5, 33, 87, 2, 45, 3, 99, 6, 777, 555, 3, 9, 11, 2, 3, 7, 5, 3, 88, 5, 3};

  public Integer[] getFiveBiggest(Integer[] input) {
    Integer[] array = new Integer[5];
    Arrays.stream(input)
        .distinct()
        .sorted((o1, o2) -> Integer.compare(o2, o1))
        .limit(5)
        .collect(Collectors.toList()).toArray(array);
    return array;
  }

  public static void main(String[] args) {
    Integer[] result = new FiveBiggest().getFiveBiggest(inputArray);
    Arrays.asList(result).forEach(System.out::println);

  }
}

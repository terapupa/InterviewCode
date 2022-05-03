package invidi.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfElements {

  public static void main(String[] arg)
  {
    /*
    * Please count and print the number of repetitions of each item in the list. */
    List<Integer> lst = Arrays.asList(4, 2, 3, 3, 4, 4, 4, 7, 1, 3, 1, 2, 3, 2, 4, 1, 0, 2, 4, 1, 4, 1, 0);
    printNumber(lst);
  }

  private static void printNumber(List<Integer> lst) {
    Map<Integer, Integer> map = new HashMap<>();
    Map<Integer, List<Integer>> mapList = new HashMap<>();
    lst.forEach(integer -> map.merge(integer, 1, Integer::sum));
    lst.forEach(integer -> mapList.merge(integer, List.of(1), (v1, v2) ->
      List.of(Integer.sum(v2.get(0), v1.get(0)))
        ));
    System.out.println("");
    map.keySet().forEach(integer -> System.out.println("key = " + integer + " -> " +  map.get(integer)));
  }
}

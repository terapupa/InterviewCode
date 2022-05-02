package invidi.code;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Sorting {

  public static void sortAlmostSorted(Iterator<Integer> sequence, int k) {
    LinkedList<Integer> ll = new LinkedList<>();
    while (sequence.hasNext()) {
      while (ll.size() < k) {
        ll.add(sequence.next());
      }
      Collections.sort(ll);
      System.out.println(ll.poll());
      ll.addLast(sequence.next());
    }
    Collections.sort(ll);
    while (!ll.isEmpty()) {
      System.out.println(ll.poll());
    }
  }

  public static void main(String[] arg)
  {
    List<Integer> lst = Arrays.asList(1, 2, 5, 3, 4, 5, 7, 9, 10, 13, 11, 12, 17, 15, 14, 18, 20, 22, 24, 25, 27, 28, 26);
    sortAlmostSorted(lst.iterator(), 3);
  }

}

package ex.code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Proteins {

  private static final ExecutorService WORKER_THREAD_POOL = Executors.newFixedThreadPool(50);


  public String protein_synthesis_part_one(String dna) {
    Set<String> strings = new HashSet<>();
    String[] split = dna.toUpperCase().split("");
    String[] triple = new String[3];
    int index = 0;
    for (String s1 : split) {
      if ("T".equals(s1)) {
        triple[index] = "U";
      } else {
        triple[index] = s1;
      }
      if (index == 2) {
        index = 0;
        String key = "";
        for (String s : triple) {
          key += s;
        }
        strings.add(key);
        triple = new String[3];
      } else {
        index++;
      }

    }
    // Write your code here
    return "";
  }


  public static String getNextProtein(String first, String second) {
    if ("A".equals(first) || "A".equals(second)
        || "B".equals(first) || "B".equals(second)
        || "C".equals(first) || "C".equals(second)
        || "D".equals(first) || "D".equals(second)) {
      if (first.equals(second)) {
        return "A";
      }
      if ("A".equals(first)) {
        return second;
      }
      if ("A".equals(second)) {
        return first;
      }
      switch (first) {
        case "B":
          if ("C".equals(second)) {
            return "D";
          } else {
            return "C";
          }
        case "C":
          if ("B".equals(second)) {
            return "D";
          } else {
            return "B";
          }
        case "D":
          if ("B".equals(second)) {
            return "C";
          } else {
            return "B";
          }
      }
    }
    return "-";
  }

  /*
   * Complete the 'pmix' function below.
   *
   * The function is expected to return a STRING.
   * The function accepts following parameters:
   *  1. STRING s
   *  2. INTEGER k
   */

  public static String pmix(String s, int k) throws InterruptedException {
    s.toUpperCase();
    StringBuilder result= new StringBuilder();
    String[] splitted = s.split("");
    long start = System.currentTimeMillis();
    do {
      String[] resArray = new String[s.length()];
      for (String s1 : resArray) {
        
      }
      CountDownLatch latch = new CountDownLatch(s.length());
      final String[] spl = splitted;
      for (int i = 0; i < s.length(); i++) {
        final int idx = i;
        WORKER_THREAD_POOL.submit(() -> {
          if (idx < s.length()-1) {
            resArray[idx] = getNextProtein(spl[idx], spl[idx+1]);
          } else {
            resArray[idx] = getNextProtein(spl[idx], spl[0]);
          }
          latch.countDown();
        });
      }
      latch.await();
      result.append("\n").append(Arrays.toString(resArray));
      splitted = resArray;

    } while (System.currentTimeMillis() - start < ((long) k) * 1000L);
    WORKER_THREAD_POOL.shutdown();
    return result.toString();
  }

  public static String pmix1(String s, int k) throws InterruptedException {
    StringBuilder result= new StringBuilder();
    int index = 0;
    int next;
    String[] splitted = s.split("");
    long start = System.currentTimeMillis();
    do {
      if (index < splitted.length - 1) {
        next = index + 1;
        splitted[index] = getNextProtein(splitted[index], splitted[next]);
      } else {
        next = 0;
        splitted[index] = getNextProtein(splitted[index], splitted[next]);
        result.append("\n").append(Arrays.toString(splitted));
      }
      index = next;
    }
    while (next != 0 || System.currentTimeMillis() - start < ((long) k) * 1000L);
    return result.toString();
  }


  public static void main(String[] args) throws IOException, InterruptedException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    String outputFile = ClassLoader.getSystemResource("expected")
        .getPath() + File.separator + "mutations.log";
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(firstMultipleInput[0]);

    int k = Integer.parseInt(firstMultipleInput[1]);

    String s = bufferedReader.readLine();

    String result = Proteins.pmix1(s, k);

    bufferedWriter.write(result);
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.flush();
    bufferedWriter.close();
  }
}


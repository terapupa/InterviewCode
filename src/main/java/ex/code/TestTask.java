package ex.code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestTask {


  private static final String[] letterGroup = {"l","e","a","g","u","e","a","p","p","s"};

  public static void main (String[] args) {

    final String word1 = "LeagueApps"; // Should return true
    final String word2 = "League Apps"; // Should return true
    final String word3 = "LeagueAppps"; // Should return false
    final String word4 = "Applause"; // Should return true
    final String word5 = "SportsDog"; // Should return false

    TestTask wf = new TestTask();

    System.out.println(wf.testWord(word1) ? "true" : "false");
    System.out.println(wf.testWord(word2) ? "true" : "false");
    System.out.println(wf.testWord(word3) ? "true" : "false");
    System.out.println(wf.testWord(word4) ? "true" : "false");
    System.out.println(wf.testWord(word5) ? "true" : "false");
  }

  private boolean testWord(final String word) {
    Map<String, Integer> letterMap = new HashMap<>();
    Map<String, Integer> wordMap = new HashMap<>();
    wordMap.entrySet().forEach(stringIntegerEntry -> {

    });

    Arrays.stream(word.split("")).filter(s -> !" ".equals(s)).forEach(key ->
        wordMap.merge(key.toLowerCase(), 1, (integer, integer2) -> Integer.sum(integer, integer2)));

    Arrays.stream(letterGroup).filter(s -> !" ".equals(s)).forEach(key ->
        letterMap.merge(key.toLowerCase(), 1, Integer::sum));

    for (String key : wordMap.keySet()) {
      Integer v1 = wordMap.get(key);
      Integer v2 = letterMap.get(key);
      if (v2 == null || v1 == null || v1 > v2) {
        return false;
      }
    }
    return true;
  }
}

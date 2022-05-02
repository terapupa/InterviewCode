package ex.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringTransormation {
  /*
  Decode an encoded string

  Given an encoded string of the format "n[encoded_string]" where n is a positive integer, print out the decoded string which is encoded_string repeated n time(s).

  Examples:


  Input: "3[a]"
  Output: "aaa"

  Input: "2[a]3[bc]"
  Output: "aabcbcbc"

  Input: "2[a3[cd]]"
  Output: "acdcdcdacdcdcd"



  */

  static List<String> getSameLevelStrings(String inputStr) {
    List<String> result = new ArrayList<>();
    String[] arr = inputStr.split("");

    StringBuilder oneString = new StringBuilder();
    int braceCount = 0;
    for (String s : arr) {
      oneString.append(s);
      if ("[".equals(s)) {
        braceCount++;
      } else if ("]".equals(s)) {
        braceCount--;
        if (braceCount == 0) {
          result.add(oneString.toString());
          oneString = new StringBuilder();
        }
      }
    }
    return result;
  }

  public static String processStr (String inputStr) {
    StringBuilder res = new StringBuilder();
    List<String> sameLevelStr = getSameLevelStrings(inputStr);
    for (String s : sameLevelStr) {
      res.append(calculate(s));
    }
    return res.toString();
  }

  private static String calculate(String str) {
    int startBracket = str.indexOf("[");
    int endBracket = str.lastIndexOf("]");
    List<String> chunkList = new ArrayList<>(Arrays.asList(str.split("[^0-9]")));
    chunkList.removeAll(Collections.singleton(""));
    int multi = Integer.parseInt(chunkList.toArray(new String[0])[0]);
    String body = str.substring(startBracket+1, endBracket);
    if (isSimple(body)) {
      return body.repeat(multi);
    } else {
      String[] chunks = body.split("[0-9]");
      return (chunks[0] + processStr(str.substring(chunks[0].length()-1))).repeat(multi);
    }
  }

  private static boolean isSimple(String toCheck) {
    return !(toCheck.contains("[") && toCheck.contains("]"));
  }

  public static void main(String[] args) {

    String input = "2[a]3[bc]4[akb3[ciod2[llv3[m]]]]";
//    String input = "2[a3[cd4[jp]]]";
    System.out.println(processStr(input));



  }
}

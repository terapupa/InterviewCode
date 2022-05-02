package ex.code;

public class Example1 {
// aaaabbbcccddddaaa
// a4b3c3d4a3


  public String convert(String input) {
    String result = "";
    String[] splitted = input.split("(?<=(.))(?!\\1)");
    for (String s : splitted) {
      result = result + s.length() + s.split("")[0];
    }

//    String[] splitted = input.split("");
//    String current = "";
//    int count = 0;
//    for (String s : splitted) {
//      if ("".equals(current) || !current.equals(s)) {
//        if (count > 0) {
//          result = result + count + current;
//        }
//        current = s;
//        count = 1;
//      } else {
//        count++;
//      }
//    }
//    result = result + count + current;
//
    return result;
//
  }

    public static void main(String[] args) {
      Example1 m = new Example1();
      String input = "aaaabbbcccdddd";
      System.out.println(m.convert(input));

    }
}

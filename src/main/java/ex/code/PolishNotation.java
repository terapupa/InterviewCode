package ex.code;

import java.util.Deque;
import java.util.LinkedList;

public class PolishNotation {

  public Integer calculate(String str) {
    Integer res = null;
    Deque<Integer> stack = new LinkedList<>();
    for (String item : str.split(",")) {
      if (isValid(item)) {
        if ("+".equals(item) || "-".equals(item) || "*".equals(item) || "/".equals(item)) {
          if (stack.size() < 2) {
            System.out.println("--> Wrong expression");
            res = null;
            break;
          } else {
            Integer v1 = stack.poll();
            Integer v0 = stack.poll();
            res = operation(item, v0, v1);
            stack.push(res);
          }
        } else {
          stack.push(Integer.parseInt(item));
        }
      } else {
        System.out.println("--> Wrong operations");
      }
    }
    return res;
  }

  private boolean isValid(String item) {
    if ("+".equals(item) || "-".equals(item) || "*".equals(item) || "/".equals(item)) {
      return true;
    }
    try {
      Integer.parseInt(item);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  private Integer operation(String oper, Integer v0, Integer v1) {
    if ("+".equals(oper)) {
      return v0+v1;
    } else if ("-".equals(oper)) {
      return v0-v1;
    } else if ("*".equals(oper)) {
      return v0*v1;
    } else if ("/".equals(oper)) {
      return v0/v1;
    } else {
      System.out.println("--> Wrong operand");
      return null;
    }
  }

  public static void main(String[] args) {
    PolishNotation pn = new PolishNotation();
//    (3-(4+2))+2*5
    System.out.println("result = " + pn.calculate("3,4,2,+,-,2,5,*,+"));
//    System.out.println("result = " + pn.calculate("03-25+*"));
    int res = (3-(-4)*5)/(7-4)*(4+6);
    System.out.println("result = " + pn.calculate("3,-4,5,*,-,7,4,-,/,4,6,+,*"));
  }
}

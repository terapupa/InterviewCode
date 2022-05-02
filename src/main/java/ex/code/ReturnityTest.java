package ex.code;

public class ReturnityTest {

  public static void fizzBuzz(int n) {
    if (n % 3 == 0 && n/3 != 0) {
      System.out.print("Fizz");
    }
    else if (n % 5 == 0 && n/3 != 0) {
      System.out.print("Buzz");
    }
    else {
      System.out.print(n);
    }
    System.out.println("");

  }

  public static void main(String[] args) {
    fizzBuzz(1);
  }
}

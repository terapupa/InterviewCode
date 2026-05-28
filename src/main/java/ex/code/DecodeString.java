package ex.code;

import java.util.Stack;

public class DecodeString {
    public static String decode(String s) {

        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();

        StringBuilder current = new StringBuilder();
        int k = 0;

        for (char c : s.toCharArray()) {

            if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
            }

            else if (c == '[') {
                countStack.push(k);
                stringStack.push(current);

                current = new StringBuilder();
                k = 0;
            }

            else if (c == ']') {

                int repeat = countStack.pop();
                StringBuilder decoded = stringStack.pop();
                decoded.append(String.valueOf(current).repeat(Math.max(0, repeat)));

                current = decoded;
            }

            else {
                current.append(c);
            }
        }

        return current.toString();
    }

    public static void main(String[] args) {

//        System.out.println(decode("3[a]"));
//        System.out.println(decode("2[a]3[bc]"));
        System.out.println(decode("2[a3[cd]]"));
    }
}

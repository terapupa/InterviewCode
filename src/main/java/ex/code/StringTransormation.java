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

    // Replace previous implementation with a recursive decoder.
    public static String processStr(String inputStr) {
        int[] idx = new int[]{0}; // use array to pass by reference
        return decode(inputStr, idx);
    }

    private static String decode(String s, int[] idx) {
        StringBuilder res = new StringBuilder();
        int n = s.length();
        while (idx[0] < n) {
            char c = s.charAt(idx[0]);
            if (c == ']') {
                // end of current level
                idx[0]++; // consume ']'
                break;
            } else if (Character.isDigit(c)) {
                // parse number
                int num = 0;
                while (idx[0] < n && Character.isDigit(s.charAt(idx[0]))) {
                    num = num * 10 + (s.charAt(idx[0]) - '0');
                    idx[0]++;
                }
                // expect '['
                if (idx[0] < n && s.charAt(idx[0]) == '[') {
                    idx[0]++; // consume '['
                }
                // decode substring inside brackets
                String decoded = decode(s, idx);
                res.append(decoded.repeat(Math.max(0, num)));
            } else {
                // plain character
                res.append(c);
                idx[0]++;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String[] samples = new String[]{
                "3[a]",
                "2[a]3[b2[cf3[dr]bl2[g]k2[y]]",
                "2[a3[cd]]"
        };

        for (String s : samples) {
            String out = processStr(s);
            System.out.println("Input:  \"" + s + "\"");
            System.out.println("Output: \"" + out + "\"");
            System.out.println();
        }
    }
}

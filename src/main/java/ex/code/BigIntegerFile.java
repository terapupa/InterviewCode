package ex.code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BigIntegerFile {

  public static void main(String[] args) {

//    try (FileWriter fr = new FileWriter("BigIntegerFile.txt")) {
//      for (int i = 0; i <= 1000; i++) {
//        fr.write(ThreadLocalRandom.current().nextInt(1, 5000) + "\n");
//      }
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    try (BufferedReader reader = new BufferedReader(new FileReader("BigIntegerFile.txt"))) {
      List<Integer> chunk = new ArrayList<>();
      int count = 0;
      while(true) {
        String value = reader.readLine();
        if (value == null) {
          System.out.println(Arrays.toString(chunk.toArray()));
          break;
        } else {
          if (count > 7) {
            count = 0;
            System.out.println(Arrays.toString(chunk.toArray()));
            chunk.clear();
          }
          chunk.add(Integer.valueOf(value));
          count++;
        }
      }
//      char[] theChars = new char[128];
//      int charsRead = reader.read(theChars, 0, theChars.length);
//      while(charsRead != -1) {
//        System.out.println(new String(theChars, 0, charsRead));
//        charsRead = reader.read(theChars, 0, theChars.length);
//      }

    } catch (IOException e) {
      e.printStackTrace();
    }



  }
}

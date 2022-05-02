package com.netapp.ci.interview;


import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

//TODO: add exception handling
public class LogMerger {

  /**
   * Merge all *.log files in inputDir and write the output file to outputFile
   *
   * @param inputDir   Absolute path to the directory where the log files are located.
   * @param outputFile Absolute path to the output file
   */
  //TODO: Add validations.
  public static void mergeLogs(String inputDir, String outputFile) throws Exception {
    ArrayList<String> resultList = new ArrayList<>();
    FileWriter fr = new FileWriter(outputFile);
    List<BufferedReader> readerList = LogUtils.createReaders(inputDir);
    readerList.forEach(bufferedReader -> {
      while (true) {
        String value = null;
        try {
          value = bufferedReader.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (value == null) {
          break;
        } else {
          resultList.add(value);
        }
      }
    });
    resultList.stream().sorted((o1, o2) -> {
      int result = 0;
      try {
        if (LogUtils.readTimestamp(o1) > LogUtils.readTimestamp(o2)) {
          result = 1;
        } else if (LogUtils.readTimestamp(o1) < LogUtils.readTimestamp(o2)) {
          result = -1;
        }
      } catch (Exception exception) {
        exception.printStackTrace();
      }
      return result;
    }).forEach(s -> {
      try {
        fr.write(s + "\n");
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    fr.flush();
    fr.close();
  }

  // no memory consumption
  public static void mergeLogs2(String inputDir, String outputFile) throws Exception {
    FileWriter fr = new FileWriter(outputFile);
    List<BufferedReader> readerList = LogUtils.createReaders(inputDir);
    List<String> resChunk = new LinkedList<>();
    while (true) {
      List<String> chunk = new LinkedList<>();
      for (BufferedReader bufferedReader : readerList) {
        String line = bufferedReader.readLine();
        if (line != null) {
          chunk.add(line);
        }
      }
      if (chunk.isEmpty()) {
        for (String s : resChunk) {
          fr.write(s + "\n");
        }
        break;
      } else {
        chunk.sort((o1, o2) -> new StrComparator().compare(o1, o2));
        List<String> toRemove = new LinkedList<>();
        for (String value : resChunk) {
          if (LogUtils.readTimestamp(value) < LogUtils.readTimestamp(chunk.get(0))) {
            fr.write(value + "\n");
            toRemove.add(value);
          } else {
            break;
          }
        }
        System.out.println("chunk size = " + resChunk.size());
        resChunk.removeAll(toRemove);
        resChunk.addAll(chunk);
        resChunk.sort((o1, o2) -> new StrComparator().compare(o1, o2));
        chunk.clear();
      }
    }
    fr.flush();
    fr.close();
  }

  static class StrComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
      int result = 0;
      try {
        if (LogUtils.readTimestamp(o1) > LogUtils.readTimestamp(o2)) {
          result = 1;
        } else if (LogUtils.readTimestamp(o1) < LogUtils.readTimestamp(o2)) {
          result = -1;
        }
      } catch (Exception exception) {
        exception.printStackTrace();
      }
      return result;
    }
  }

}




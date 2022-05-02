package ex.code;

import static javax.swing.UIManager.put;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class Synth {

  private String start_codon = "AUG";
  private String stop_codon = "STOP";
  private Map<String, String> codon_to_amino_acid = new HashMap<String, String>() {{
    put("AUG", "Met");
    put("CAA", "Gln");
    put("CAG", "Gln");
    put("GGU", "Gly");
    put("GCG", "Ala");
    put("UUU", "Phe");
    put("UUC", "Phe");
    put("UGG", "Trp");
    put("UAA", stop_codon);
    put("UAG", stop_codon);
    put("UGA", stop_codon);
  }};

  /*
   * Complete the functions below.
   */

  public String protein_synthesis_part_one(String dna) {
    return Arrays.stream(Arrays.stream(dna.toUpperCase().split("")).map(s -> {
      if ("T".equals(s)) {
        return "U";
      }
      return s;
    }).collect(Collectors.joining())
        .split("(?<=\\G.{3})"))
        .map(s -> codon_to_amino_acid.get(s)).distinct().filter(s -> !stop_codon.equals(s)).sorted()
        .collect(Collectors.joining(" "));
//
//
//
//
//        Set<String> strings = new HashSet<>();
//    String[] split = dna.toUpperCase().split("");
//    String[] triple = new String[3];
//    int index = 0;
//    for (String s1 : split) {
//      if ("T".equals(s1)) {
//        triple[index] = "U";
//      } else {
//        triple[index] = s1;
//      }
//      if (index == 2) {
//        index = 0;
//        String amino = codon_to_amino_acid.get(String.join("", triple));
//        if (amino == null || stop_codon.equals(amino)) {
//          break;
//        }
//        strings.add(amino);
//        triple = new String[3];
//      } else {
//        index++;
//      }
//
//    }
//    System.out.println(String.join(" ", strings));
//    return String.join(" ", strings);
  }

  public String protein_synthesis_part_two(String dna) {
    String res = dna.chars().filter(Character::isUpperCase).mapToObj(value -> String.valueOf((char) value)).collect(Collectors.joining());
    // Write your code here
    return "";
  }


  public static void main(String[] args) throws Exception {
    Synth solution = new Synth();
    solution.protein_synthesis_part_one("CAAATGCAGGCGTAA");
    solution.protein_synthesis_part_two("caaATGcagGCGtaa");
    System.out.println("bbb");

    Properties p = new Properties();


    try {
      throw new NullPointerException();

    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }

  }
//    PrintWriter writerObj = new PrintWriter(System.getenv("OUTPUT_PATH"));
//    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//    int part = Integer.parseInt((reader.readLine()).trim());
//    String dna = reader.readLine();
//    String result = "";
//
//    Synth solution = new Synth();
//    if (part == 1){
//      result = solution.protein_synthesis_part_one(dna);
//    } else {
//      result = solution.protein_synthesis_part_two(dna);
//    }
//
//    writerObj.write(result);
//    writerObj.flush();
//    writerObj.close();

}

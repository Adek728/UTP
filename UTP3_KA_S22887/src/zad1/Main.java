/**
 *
 *  @author Krzywnicki Adrian S22887
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args){

      Function<String, List<String>> flines = (s) -> {
          List<String> result = new ArrayList();
          BufferedReader br = null;
          try {

              br = new BufferedReader(new FileReader(s));

              String line;
              while ((line = br.readLine()) != null) {
                  result.add(line);
              }

          } catch (IOException e) {
              e.printStackTrace();
          } finally {
              if (br != null) {
                  try {
                      br.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }
          return result;
      };

      Function<List<String>, String> join= (s) -> {
          String x = String.join(" ", s);
          return x;
      };

      Function<String, List<Integer>> collectInts = (s) -> {
          List<Integer> result = new ArrayList<>();
          Matcher matcher = Pattern.compile("\\d+").matcher(s);
          while(matcher.find()){
              result.add(Integer.parseInt(matcher.group()));
          }
          return result;
      };

      Function<List<Integer>, Integer> sum = (s) -> {
          int suma = 0;
          for (Integer item : s) {
              suma += item;
          }
          return suma;
      };
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych*/


      String fname = System.getProperty("user.home") + "/LamComFile.txt";
      InputConverter<String> fileConv = new InputConverter<>(fname);
      List<String> lines = fileConv.convertBy(flines);
      String text = fileConv.convertBy(flines, join);
      List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
      Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

      System.out.println(lines);
      System.out.println(text);
      System.out.println(ints);
      System.out.println(sumints);

      List<String> arglist = Arrays.asList(args);
      InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
      sumints = slistConv.convertBy(join, collectInts, sum);
      System.out.println(sumints);
  }
}

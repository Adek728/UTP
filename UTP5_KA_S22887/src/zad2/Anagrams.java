/**
 *
 *  @author Krzywnicki Adrian S22887
 *
 */

package zad2;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Anagrams {

    private List<String> lista;

    public Anagrams(String slowa){
            this.lista = new ArrayList<>();
            BufferedReader br = null;
            try {

                br = new BufferedReader(new FileReader(slowa));

                String line;
                while ((line = br.readLine()) != null) {
                    this.lista.addAll(Arrays.asList(line.split(" ")));
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
    }
    public static String sortSlowo(String s) {
        char[] znaki = s.toCharArray();

        for(int i = 0, zmiany = 1; i < znaki.length-1  && zmiany != 0 ; i++) {
            zmiany = 0;
            for(int j = 0; j < znaki.length-1; j++) {
                if(znaki[j] > znaki[j+1]) {
                    char pom = znaki[j+1];
                    znaki[j+1] = znaki[j];
                    znaki[j] = pom;
                    zmiany++;
                }
            }
        }
        return String.valueOf(znaki);
    }
    public boolean CzyAnagram(String x1, String x2){
        if(x1.length() != x2.length()){
            return false;
        }
        return sortSlowo(x1).equals(sortSlowo(x2));
    }

    public List<ArrayList<String>> getSortedByAnQty(){
        ArrayList<ArrayList<String>> tab = new ArrayList<>();

        for (String s : this.lista) {
            boolean x = false;
            for (ArrayList<String> strings : tab) {
                String slowo = strings.get(0);

                if(CzyAnagram(slowo, s)){
                    strings.add(s);
                    x = true;
                    break;
                }
            }
            if (!x) {
                ArrayList<String> newList = new ArrayList<>();
                newList.add(s);
                tab.add(newList);
            }
        }

        return tab
                .stream()
                .sorted((el1, el2) -> {
                    int diff = el2.size() - el1.size();
                    if (diff == 0) {
                        return el1.get(0).compareTo(el2.get(0));
                    }
                    return diff;
                })
                .collect(Collectors.toList());

    }

    public String getAnagramsFor(String slowo) {
        return null;
    }
}

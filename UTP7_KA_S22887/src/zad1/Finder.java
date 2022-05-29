/**
 *
 *  @author Krzywnicki Adrian S22887
 *
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Finder {
    private ArrayList<String> lista;

    public Finder(String nazwa){
        this.lista = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(nazwa));

            String line;
            while ((line = br.readLine()) != null) {
                if(line.startsWith("//")){ }
                else
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

    public int getIfCount(){
        int wynik = 0;
        for (int i = 0; i < this.lista.size(); i++) {
            if(this.lista.get(i).equals("if") && this.lista.get(i + 1).equals("{")){
                wynik++;
            }
            if(this.lista.get(i).equals("if{")){
                wynik++;
            }
        }
        return wynik;
    }

    public int getStringCount(String slowo){
        int wynik = 0;
        for (int i = 0; i < this.lista.size(); i++) {
            if(this.lista.get(i).equals(slowo)){
                wynik++;
            }
        }
        return wynik;
    }
}    

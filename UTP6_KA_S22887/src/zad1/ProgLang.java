package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ProgLang {
    private final ArrayList<String> lista;

    public ProgLang(String nazwaPliku) {
        this.lista = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(nazwaPliku));

            String line;
            while ((line = br.readLine()) != null) {
                this.lista.add(line);
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

    public LinkedHashMap<String, LinkedHashSet<String>> getLangsMap() {
        LinkedHashMap<String, LinkedHashSet<String>> mapa = new LinkedHashMap<String, LinkedHashSet<String>>();

        for (String linia : this.lista) {
            String[] liniaBezTabulacji = linia.split("\\t");
            String jezyk = "";

            for (int i = 0; i < liniaBezTabulacji.length; i++) {
                String element = liniaBezTabulacji[i];

                if (i == 0) {
                    jezyk = element;
                }else {
                    LinkedHashSet<String> tab = mapa.get(jezyk);

                    if (tab == null) {
                        tab = new LinkedHashSet<String>();
                        mapa.put(jezyk, tab);
                    }
                    tab.add(element);
                }
            }
        }
        return mapa;
    }

    public LinkedHashMap<String, LinkedHashSet<String>> getProgsMap() {
        LinkedHashMap<String, LinkedHashSet<String>> mapa = new LinkedHashMap<String, LinkedHashSet<String>>();

        for (String linia : this.lista) {
            String[] liniaBezTabulacji = linia.split("\\t");
            String jezyk = "";

            for (int i = 0; i < liniaBezTabulacji.length; i++) {
                String element = liniaBezTabulacji[i];

                if (i == 0) {
                    jezyk = element;
                } else {
                    LinkedHashSet<String> tab = mapa.get(element);

                    if (tab == null) {
                        tab = new LinkedHashSet<String>();
                        mapa.put(element, tab);
                    }
                    tab.add(jezyk);
                }
            }
        }
        return mapa;
    }


    public LinkedHashMap<String, LinkedHashSet<String>> getLangsMapSortedByNumOfProgs() {
        return null;
    }

    public LinkedHashMap<String, LinkedHashSet<String>> getProgsMapSortedByNumOfLangs() {
        return null;
    }
    public LinkedHashMap<String, LinkedHashSet<String>> getProgsMapForNumOfLangsGreaterThan(int i) {
        return null;
    }

    public void sorted(){

    }
    public void filtered(){

    }

}

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
import java.util.Comparator;

public class CustomersPurchaseSortFind implements Comparable<Purchase>{
    private ArrayList<Purchase> listaZakupow;

    @Override
    public int compareTo(Purchase o) {
        return 0;
    }

    public void readFile(String fname) {
        this.listaZakupow = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fname));

            String line;
            while((line = br.readLine()) != null) {
                this.listaZakupow.add(new Purchase(line));
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

    public Comparator<Purchase> komparatorNazwiska = (x1, x2) -> {
            int x = x1.nazwisko.compareTo(x2.nazwisko);
            if(x == 0){
                return x1.id.compareTo(x2.id);
            }
            return x;
    };

    public Comparator<Purchase> komparatorKosztow = (x1, x2) -> {
        int x = (int)(x2.getCost() - x1.getCost());

        if(x == 0){
            return x1.id.compareTo(x2.id);
        }
        return x;
    };

    public Comparator<Purchase> getFilter(String slowo){
        if(slowo.equals("Nazwiska")){
            return komparatorNazwiska;
        }

        if(slowo.equals("Koszty")){
            return komparatorKosztow;
        }
        return null;
    }

    public void showSortedBy(String nazwa) {
        System.out.println(nazwa);
        this.listaZakupow
                .stream()
                .sorted(this.getFilter(nazwa))
                .forEach(p -> System.out.println(p.toString(nazwa)));
        System.out.println();
    }

    public void showPurchaseFor(String numer) {
        System.out.println("Klient " + numer);
        this.listaZakupow
                .stream()
                .filter((p) -> p.id.equals(numer))
                .forEach(p -> System.out.println(p.toString(numer)));
        System.out.println();
    }


}

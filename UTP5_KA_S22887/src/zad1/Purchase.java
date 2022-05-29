/**
 *
 *  @author Krzywnicki Adrian S22887
 *
 */

package zad1;


public class Purchase {
    String id;
    String imie;
    String nazwisko;
    String produkt;
    double cena;
    double ilosc;

    public Purchase(String nazwa){
        String[]tab = nazwa.split(";");
        this.id = tab[0];
        String [] tabwimionach = tab[1].split(" ");
        this.nazwisko = tabwimionach[0];
        this.imie = tabwimionach[1];
        this.produkt = tab[2];
        this.cena = Double.parseDouble(tab[3]);
        this.ilosc = Double.parseDouble(tab[4]);
    }

    public double getCost(){
        return this.cena * this.ilosc;
    }

    public StringBuilder toString(String nazwa){
        StringBuilder x1 = new StringBuilder();
        x1.append(this.id).append(";")
                .append(this.nazwisko)
                .append(" ")
                .append(this.imie).append(";")
                .append(this.produkt).append(";")
                .append(this.cena).append(";")
                .append(this.ilosc);
        if(!nazwa.equals("Koszty")) {
            return x1;
        }

        x1.append(" (koszt: ")
                .append(this.getCost())
                .append(")");

        return x1;
    }
}

/**
 *
 *  @author Krzywnicki Adrian S22887
 *
 */

package zad1;


import java.util.*;


public class ListCreator <T>{
    java.util.List<T> listaNowa = new ArrayList<>();

    public ListCreator(ArrayList<T> listaNowa) {
        this.listaNowa = listaNowa;
    }

    static <T> ListCreator<T> collectFrom(List<T> listaNowa) {
        ListCreator listCreator = new ListCreator(new ArrayList<>(listaNowa));
        return listCreator;
    }

    public ListCreator<T> when(Selector<T> sell) {
        int aktIndeks = 0;
        for (int i = 0; i < listaNowa.size(); i++) {
            if(sell.select(listaNowa.get(i))) {
                listaNowa.set(aktIndeks, listaNowa.get(i));
                aktIndeks++;
            }
        }
        for (int i = listaNowa.size() - 1; i >= aktIndeks; i--) {
            listaNowa.remove(i);
        }

        return this;
    }

    public<U> List<U> mapEvery(Mapper<T, U> mapp) {
        List<U> afterMap = new ArrayList<>();

        for(T t : listaNowa){
            afterMap.add(mapp.map(t));
        }
        return afterMap;
    }
}  

package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
public class ListCreator<T>{

    private List<T> listaNowa;

    public ListCreator(List<T> listaNowa){
        this.listaNowa = listaNowa;
    }

    static <T> ListCreator<T> collectFrom(List<T> listaNowa) {
        ListCreator listCreator = new ListCreator(new ArrayList<>(listaNowa));
        return listCreator;
    }

    public ListCreator<T> when(Function<T, Boolean> sell){
        int aktIndeks = 0;
        for (int i = 0; i < listaNowa.size(); i++) {
            if(sell.apply(listaNowa.get(i))) {
                listaNowa.set(aktIndeks, listaNowa.get(i));
                aktIndeks++;
            }
        }
        for (int i = listaNowa.size() - 1; i >= aktIndeks; i--) {
            listaNowa.remove(i);
        }

        return this;
    }

    public <S> List<S> mapEvery(Function<T,S> mapp){
        List<S> afterMap = new ArrayList<>();

        for(T t : listaNowa){
            afterMap.add(mapp.apply(t));
        }
        return afterMap;
    }
}

package zad1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList <T> extends ArrayList<T>{

    public XList(Collection <T> coll) {
        super(coll);
    }

    public XList(T ... args){
        this(Arrays.asList(args));
    }

    public static <T> XList<T> of(T ... args){
        return new XList<>(args);
    }

    public static <T> XList<T> of(Collection<T> args){
        return new XList<>(args);
    }

    public static XList<String> tokensOf(String napis, String sep){
        List<String> nowalista = Arrays.asList(napis.split(sep));
        return XList.of(nowalista);
    }

    public static XList<String> tokensOf(String napis){
        List<String> nowalista = Arrays.asList(napis.split("\\s"));
        return XList.of(nowalista);
    }

    public static XList<String> charsOf(String napis){
        List<String> nowalista = Arrays.asList(napis.split(""));
        return XList.of(nowalista);
    }


    public XList<T> union(Collection<T> coll){
        XList<T> nowaLista = new XList<>();
        for (int i = 0; i < this.size(); i++) {
            nowaLista.add(this.get(i));
        }
        nowaLista.addAll(coll);

        return nowaLista;
    }

    public XList<T> union(T ... args){
        return this.union(XList.of(args));
    }

    public XList<T> diff(Collection<T> coll){
        XList<T> nowaLista = new XList<>();

        for (int i = 0; i < this.size(); i++) {
            if(!coll.contains(this.get(i))){
                nowaLista.add(this.get(i));
            }
        }

        return nowaLista;
    }


    public XList<T> unique(){
        return new XList(this.stream().distinct().collect(Collectors.toList()));
    }

    public <R> XList<R> collect(Function<T, R> function){
        List<R> x = stream()
                .map(function::apply)
                .collect(Collectors.toList());
        return new XList<>(x);
    }
}

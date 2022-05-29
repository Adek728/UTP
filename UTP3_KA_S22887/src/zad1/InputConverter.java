package zad1;

import java.util.List;
import java.util.function.Function;

public class InputConverter<T> {
    private static String name = "";
    private static List<String> lista;

    public InputConverter(String name){
        this.name = name;
    }
    public InputConverter(List<String> list){
        this.lista = list;
        this.name = "";
    }

    public <R> R convertBy(Function<?,?>... tablicaFunkcji){
        Function result = tablicaFunkcji[0];

        for (int i = 1; i < tablicaFunkcji.length; i++) {
            result = result.andThen(tablicaFunkcji[i]);
        }
        if(this.name == ""){
            return (R) result.apply(this.lista);
        }else{
            return (R) result.apply(this.name);
        }

    }
}

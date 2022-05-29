package zad2;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T>{

    public Maybe() {

    }

    public static<T> Maybe of(T x){

        if(x == null){
            System.out.println("Maybe is empty");
            return null;
        }else{
            System.out.println("Maybe has value " + x);
            return (Maybe) x;
        }
    }

    public void ifPresent(Consumer cons){

    }

    public Maybe map(Function func){

    }

    public T get(){

    }


    public boolean isPresent(){

    }

    public T orElse(T defVal){

    }

    public Maybe filter(Predicate pred){

    }
}

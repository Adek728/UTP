/**
 *
 *  @author Krzywnicki Adrian S22887
 *
 */

package zad1;


public interface Mapper <T, U> { // Uwaga: interfejs musi być sparametrtyzowany
    public U map(T arg);
}  

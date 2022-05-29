package zad1.b;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final ArrayBlockingQueue<Integer> tablica;
    private final int ilosc;
    private Lock lock = new ReentrantLock();

    public Buffer(int ilosc) {
        tablica = new ArrayBlockingQueue(ilosc);
        this.ilosc = ilosc;
    }

    public int get(){
        lock.lock();
        try{
            return tablica.remove();
        }finally {
            lock.unlock();
        }
    }

    public void put(Integer i){
        lock.lock();
        try{
            tablica.add(i);
        }finally {
            lock.unlock();
        }
    }

    public boolean czyPelny(){
        return tablica.size() == ilosc;
    }
    public boolean czyPusty(){
        return tablica.size() == 0;
    }
}

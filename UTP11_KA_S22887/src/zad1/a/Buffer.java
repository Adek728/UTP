package zad1.a;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private int[] tablica;
    private int ilosc = 0;
    private Lock lock = new ReentrantLock();

    public Buffer(int size) {
        tablica = new int[size];
    }

    public int get() {
        int wyjecie = tablica[0];
        if (ilosc - 1 >= 0) {
            lock.lock();
            try {
                for (int i = 1; i < ilosc; i++) {
                    tablica[i - 1] = tablica[i];
                }
                ilosc--;
            } finally {
                lock.unlock();
            }
        }
        return wyjecie;
    }

    public void put(int i) throws InterruptedException {
        lock.lock();
        try {
            tablica[ilosc] = i;
            ilosc++;
        } finally {
            lock.unlock();
        }
    }

    public boolean czyPelny() {
        return ilosc == tablica.length;
    }

    public boolean czyPusty() {
        return ilosc == 0;
    }


}

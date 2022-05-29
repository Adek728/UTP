package zad1.a;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer b = new Buffer(3);
        Consumer c = new Consumer(b);
        Producer p = new Producer(b);
        Thread pTheard = new Thread(p);
        Thread cTheard = new Thread(c);
        pTheard.start();
        cTheard.start();
        Thread.sleep(15000);
        pTheard.stop();
        cTheard.stop();
    }
}

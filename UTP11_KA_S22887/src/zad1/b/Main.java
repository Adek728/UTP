package zad1.b;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer b = new Buffer(10);
        Consumer c = new Consumer(b);
        Producer p = new Producer(b);
        Thread cTheard = new Thread(c);
        Thread pTheard = new Thread(p);
        cTheard.start();
        pTheard.start();
        Thread.sleep(15000);
        cTheard.stop();
        pTheard.stop();
    }


}

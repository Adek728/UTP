package zad1.a;

public class Consumer implements Runnable {
    private final Buffer b;


    public Consumer(Buffer b) {
        this.b = b;
    }

    @Override
    public void run() {
        while (true) {
            if (b.czyPusty()) {
                System.out.println("Brak produktu");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Pobrano produkt: " + b.get());
            try {
                Thread.sleep((int) (Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

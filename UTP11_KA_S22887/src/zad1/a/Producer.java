package zad1.a;


public class Producer implements Runnable {
    private final Buffer b;


    public Producer(Buffer b) {
        this.b = b;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (b.czyPelny()) {
                    System.out.println("Brak miejsca");
                    Thread.sleep(2000);
                }
                int p = (int) (Math.random() * 100);
                b.put(p);
                System.out.println("Wstawiono: " + p);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep((int) (Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package zad1;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import zad1.Controller;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class Call implements Callable<String> {

    Button przycisk;
    Text text;
    ExecutorService executorService;

    public Call(Button przycisk, Text text, ExecutorService executorService) {
        this.przycisk = przycisk;
        this.text = text;
        this.executorService = executorService;
    }

    boolean[] wait = {false};

    Controller x = new Controller();
    boolean[] z = x.isStop();

    @Override
    public String call() {

        int wynik = 0;
        int limit = (1000 * Integer.parseInt(przycisk.getId()));
        while (wynik < limit) {
            try {
                if (wait[0]) synchronized (this) {
                    this.wait();
                }
                int dodaj = (int) (Math.random() * 100);
                wynik += dodaj;
                text.setText(text.getText() + "Thread " + przycisk.getId() + " (limit " + limit + "): " + dodaj + ", " + "sum = " + wynik + "\n");
                Thread.sleep((int) (Math.random() * 1000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (wynik >= limit) {
            text.setText(text.getText() + "Thread " + przycisk.getId() + ": Done!\n");
            przycisk.setText("T" + przycisk.getId() + " Done");
            przycisk.setDisable(true);
        }
        return null;
    }

    public boolean[] getWait() {
        return wait;
    }

}

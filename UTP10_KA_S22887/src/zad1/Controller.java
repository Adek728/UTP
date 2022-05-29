package zad1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.concurrent.*;

public class Controller {
    private int ilosc = 0;
    ExecutorService executorService = Executors.newCachedThreadPool();
    FutureTask<String> wyniki;
    @FXML
    HBox okno;
    @FXML
    Button nowy;
    @FXML
    Button STOP;
    @FXML
    Text wyswietlenie;

    boolean[] stop = {false};

    public boolean[] isStop() {
        return this.stop;
    }

    public Controller(){
    }

    public void stop() {
        wyswietlenie.setText(wyswietlenie.getText() + "STOP!\n");
        STOP.setDisable(true);
        nowy.setDisable(true);
        for (int i = 0; i < ilosc; i++) {
            okno.getChildren().get(i).setDisable(true);
        }
        this.stop[0] = true;


    }

    public void doWstrzymania(Call call){
        synchronized (call){
            call.notify();
        }
    }

    public void przerywanie(Call call) throws InterruptedException {
        if (stop[0]) synchronized (this) {
            call.wait();
        }
    }

    public void addThread() {
        ilosc++;
        Button button = new Button("T" + ilosc);
        button.setMinWidth(75);
        button.setId(String.valueOf(ilosc));
        button.setOnAction(new EventHandler<>() {
            Call call = new Call(button, wyswietlenie, executorService);
            boolean start = true;
            boolean zaczete = false;
            boolean zatrzymane = false;
            boolean zamkniecie = false;

            @Override
            public void handle(ActionEvent event) {
                if(start){
                    try {
                        przerywanie(call);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    start = false;
                    zaczete = true;
                    button.setText("Suspend T" + button.getId());
                    wyniki = new Task(call);
                    executorService.submit(wyniki);
                } else if(zaczete){
                    try {
                        przerywanie(call);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    zaczete = false;
                    zatrzymane = true;
                    wyswietlenie.setText(wyswietlenie.getText() + "Thread " + button.getId() + ": Suspended!\n");
                    button.setText("Continue T" + button.getId());
                    call.getWait()[0] = true;
                    doWstrzymania(call);
                } else if(zatrzymane){
                    try {
                        przerywanie(call);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    zaczete = true;
                    zatrzymane = false;
                    wyswietlenie.setText(wyswietlenie.getText() + "Thread " + button.getId() + ": Continue!\n");
                    button.setText("Suspend T" + button.getId());
                    call.getWait()[0] = false;
                    doWstrzymania(call);
                } else if(zamkniecie){
                    zaczete = false;
                    zatrzymane = false;
                    wyswietlenie.setText(wyswietlenie.getText() + "Thread " + button.getId() + ": Cancelled!\n");
                    button.setText("T" + button.getId() + " Cancelled");
                    call.getWait()[0] = true;
                    button.setDisable(true);
                }
            }
        });
        okno.getChildren().add(button);
    }
}


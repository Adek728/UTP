package zad1;

public class StringTask implements Runnable{
    private String napis;
    private String konkatenacja = "";
    private int ilosc;
    private TaskState state;

    @Override
    public void run() {
            konkatenacja = konkatenacja + napis;
    }

    public StringTask(String napis, int liczba) {
        state = TaskState.CREATED;
        this.napis = napis;
        this.ilosc = liczba;
    }

    public String getResult(){
        return this.konkatenacja;
    }

    public TaskState getState(){
        return this.state;
    }

    public void start(){
        this.state = TaskState.RUNNING;
        (new Thread(() ->{
            for(int i = 0; i < this.ilosc; i++){
                run();
            }
            state = TaskState.READY;
        })).start();
    }

    public void abort(){
        this.state = TaskState.ABORTED;
    }

    public boolean isDone(){
        return this.state == TaskState.READY || this.state == TaskState.ABORTED;
    }
}

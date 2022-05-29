package zad1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Task extends FutureTask<String> {

    public Task(Callable<String> callable) {
        super(callable);
    }

    @Override
    protected void done() {
    }
}

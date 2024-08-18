package unsw.expect;

import java.util.function.Consumer;

public class Test<T, C extends Consumer<T>> implements Consumer<T>, Runnable {
    private T arg;
    private C consumer;


    public Test(T arg, C consumer) {
        this.arg = arg;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        consumer.accept(arg);
    }

    @Override
    public void accept(T arg0) {
        this.arg = arg0;
    }

}

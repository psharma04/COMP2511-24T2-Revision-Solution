package unsw.expect;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class ExpectParaIterator<T, C extends Consumer<T>, L extends List<T>>
        implements Iterator<Runnable> {

    private C consumer;
    private L parameters;
    private int count;

    public ExpectParaIterator(C consumer, L parameters) {
        this.consumer = consumer;
        this.parameters = parameters;
        this.count = 0;
    }

    @Override
    public boolean hasNext() {
        return count < parameters.size();
    }

    @Override
    public Runnable next() {
        if (!hasNext()) {
            throw new NoSuchElementException("no more ele");
        }
        var x = new Test<T, Consumer<T>>(parameters.get(count), consumer);
        count++;
        return x;
    }


}

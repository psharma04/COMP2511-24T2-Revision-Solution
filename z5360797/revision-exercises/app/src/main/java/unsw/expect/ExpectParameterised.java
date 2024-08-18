package unsw.expect;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ExpectParameterised<T, C extends Consumer<T>, L extends List<T>>
        implements Iterable<Runnable> {
    private C consumer;
    private L parameters;

    public ExpectParameterised(C consumer, L parameters) {
        this.consumer = consumer;
        this.parameters = parameters;
    }

    @Override
    public Iterator<Runnable> iterator() {
        return new ExpectParaIterator<T, Consumer<T>, List<T>>(consumer, parameters);
    }

    public void evaluateAll() throws Throwable {
        for (var p : parameters) {
            try {
                consumer.accept(p);
            } catch (Exception e) {
                throw new ExpectationFailedException("failed hehe");
            }
        }
    }

}

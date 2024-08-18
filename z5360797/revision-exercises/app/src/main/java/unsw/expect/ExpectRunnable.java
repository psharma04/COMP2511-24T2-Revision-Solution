package unsw.expect;

public class ExpectRunnable<E extends Runnable> extends Expect<E> {
    public ExpectRunnable(E exec) {
        super(exec);
    }

    public ExpectRunnable(boolean bool) {
        super(bool);
    }

    public <X extends Exception> ExpectRunnable<E> toThrow(Class<X> clz) {
        try {
            execute();
            return new ExpectRunnable<>(false);
        } catch (Throwable e) {
            if (e.getClass().equals(clz))
                return new ExpectRunnable<E>(true);
            else
                return new ExpectRunnable<>(false);
        }
    }

    public void execute() throws Throwable {
        getInner().run();

    }
}

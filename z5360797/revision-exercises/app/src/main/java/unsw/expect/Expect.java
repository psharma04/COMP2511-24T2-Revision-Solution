package unsw.expect;

public class Expect<E> {

    private E inner;
    private boolean eval;

    protected Expect() {}

    public Expect(E obj) {
        this.inner = obj;
        this.eval = true;
    }

    public Expect(boolean eval) {
        this.eval = eval;
    }

    public Expect<E> toEqual(E other) {
        return new Expect<>(other.equals(this.inner));
    }

    public <T extends Comparable<E>> Expect<E> lessThan(T other) {
        return new Expect<>(other.compareTo(this.inner) > 0);
    }


    public <T extends Comparable<E>> Expect<E> greaterThanOrEqualTo(T other) {
        return new Expect<>(other.compareTo(this.inner) <= 0);
    }


    public Expect<E> not() {
        return new Expect<>(!eval);
    }

    public Expect<E> skip() {
        return new Expect<>(true);
    }

    public void evaluate() {
        if (eval == false) {
            throw new ExpectationFailedException("falied lmao");
        }

    }

    protected E getInner() {
        return inner;
    }



}

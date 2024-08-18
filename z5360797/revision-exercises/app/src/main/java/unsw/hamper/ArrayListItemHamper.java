package unsw.hamper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.ToIntFunction;

/**
 * A hamper implemented using an ArrayList.
 *
 * @author Matthew Perry
 *
 * @invariant for all c in counts, c.getCount() > 0
 *
 * @param <E>
 */
public class ArrayListItemHamper<E extends Item> implements Hamper<E> {

    private ArrayList<Count<E>> counts;

    /**
     * Create a new empty hamper.
     */
    public ArrayListItemHamper() {
        this.counts = new ArrayList<Count<E>>();
    }

    private Count<E> getCount(Object o) {
        for (Count<E> c : counts)
            if (c.getElement().equals(o))
                return c;
        return null;
    }

    @Override
    public void add(E e) {
        add(e, 1);
    }

    @Override
    public void add(E e, int n) {
        Count<E> c = getCount(e);
        if (c != null) {
            c.incrementCount(n);
        } else if (n > 0) {
            counts.add(new Count<E>(e, n));
        }
    }

    @Override
    public void remove(E e) {
        remove(e, 1);
    }

    @Override
    public void remove(E e, int n) {
        Count<E> count = getCount(e);
        if (count == null)
            return;
        count.decrementCount(n);
        if (count.getCount() <= 0) {
            counts.remove(count);
        }
    }

    @Override
    public int count(Object o) {
        Count<E> c = getCount(o);
        if (c != null)
            return c.getCount();
        return 0;
    }

    public <T extends Item> int countType(Class<T> c) {
        return counts.stream().filter(e -> e.getElement().getClass() == c)
                .mapToInt(e -> e.getCount()).sum();
    }

    @Override
    public int size() {
        return counts.stream().mapToInt(Count::getCount).sum();
    }

    @Override
    public Hamper<E> sum(Hamper<? extends E> hamper) {
        Hamper<E> newHamper = new ArrayListItemHamper<>();
        Iterator<Count<E>> me = this.iterator();
        while (me.hasNext()) {
            Count<E> count = me.next();
            newHamper.add(count.getElement(), count.getCount());
        }
        Iterator<? extends Count<? extends E>> other = hamper.iterator();
        while (other.hasNext()) {
            Count<? extends E> count = other.next();
            newHamper.add(count.getElement(), count.getCount());
        }
        return newHamper;
    }

    @Override
    public Iterator<Count<E>> iterator() {
        return counts.iterator();
    }

    /**
     * For this method, hampers should be the same class to be equal (ignore the generic type
     * component). For example, a CreativeHamper cannot be equal to a FruitHamper, And a FruitHamper
     * cannot be equal to an ArrayListItemHamper<Fruit>, However an ArrayListItemHamper<Fruit> can
     * be equal to a ArrayListItemHamper<Item> if they both only contain fruit. HINT: use getclass()
     * to compare objects.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (this == o)
            return true;
        if (this.getClass() != o.getClass())
            return false;
        @SuppressWarnings("unchecked")
        var other = (ArrayListItemHamper<E>) o;
        if (sizeCount() != other.sizeCount())
            return false;
        for (var c : counts) {
            var c2 = other.getCount(c.getElement());
            if (c.getElement().getClass() != c2.getElement().getClass())
                return false;
            if (c.getCount() != c2.getCount())
                return false;
        }

        return true;
    }

    public int sizeCount() {
        return counts.size();
    }

    /**
     * 
     * @return price of the hamper - for ArrayListItemHamper, this should be the sum of the prices
     *         of items with each price multiplied by the number of times that item occurs
     */
    @Override
    public int getPrice() {
        return counts.stream().mapToInt(c -> c.getElement().getPrice() * c.getCount()).sum();
    }

    @Override
    public String toString() {
        return counts.toString();
    }
}

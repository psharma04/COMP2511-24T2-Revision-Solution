package unsw.cycle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cycle<E> implements Iterable<E> {
    /**
     * finite sequence of elements which will be repeated in the cycle infinitely
     */
    private final ArrayList<E> sublist;

    public Cycle(List<E> initialSublist) {
        sublist = new ArrayList<>(initialSublist);
    }

    /**
     * return the size of the cycle - infinity if 1 or more elements, otherwise 0
     */
    public double size() {
        if (isEmpty()) {
            return 0;
        } else {
            return Double.POSITIVE_INFINITY;
        }
    }

    /**
     * return whether the cycle is empty
     */
    public boolean isEmpty() {
        return sublist.isEmpty();
    }

    /**
     * return whether the cycle contains this object
     */
    public boolean contains(Object o) {
        return sublist.contains(o);
    }

    /**
     * add element to the cycle
     */
    public boolean add(E e) {
        return sublist.add(e);
    }

    /**
     * remove first instance of the input value
     */
    public boolean remove(Object o) {
        return sublist.remove(o);
    }

    public String toString() {
        return getClass().getSimpleName() + " items=" + sublist;
    }

    public String toString2() {
        String ret = "";
        for (var s : sublist) {
            ret += s.toString();
        }
        return ret;
    }

    /**
     * create and return iterator for the cycle (infinitely repeating)
     */
    @Override
    public Iterator<E> iterator() {
        return new CycleIterator<>(sublist);
    }

    public List<E> getSimplifiedSublist() {
        int n = sublist.size();
        for (int len = 1; len <= n / 2; len++) {
            if (n % len == 0) {
                boolean isRepeating = true;
                for (int i = 0; i < n; i++) {
                    if (!sublist.get(i).equals(sublist.get(i % len))) {
                        isRepeating = false;
                        break;
                    }
                }
                if (isRepeating) {
                    return sublist.subList(0, len);
                }
            }
        }
        return sublist; // If no repeating pattern is found, return the original list
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Cycle))
            return false;
        Cycle<?> other = (Cycle<?>) o;
        if (getSimplifiedSublist().size() != other.getSimplifiedSublist().size())
            return false;
        String concat = toString2() + toString2();
        String otherString = other.toString2();
        return concat.contains(otherString);
    }
}

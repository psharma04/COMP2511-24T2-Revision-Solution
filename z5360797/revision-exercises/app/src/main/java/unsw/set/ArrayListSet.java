package unsw.set;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListSet<T> implements Set<T> {

    private List<T> l;

    public ArrayListSet() {
        l = new ArrayList<>();
    }

    @Override
    public Iterator<T> iterator() {
        return l.iterator();
    }

    @Override
    public void add(T e) {
        if (l.contains(e))
            return;
        l.add(e);
    }

    @Override
    public void remove(T e) {
        l.remove(e);
    }

    @Override
    public boolean contains(Object e) {
        return l.contains(e);
    }

    @Override
    public int size() {
        return l.size();
    }

    @Override
    public boolean subsetOf(Set<?> other) {
        for (T e : l) {
            if (!other.contains(e))
                return false;
        }
        return true;
    }

    @Override
    public Set<T> union(Set<? extends T> other) {
        ArrayListSet<T> l2 = new ArrayListSet<>();
        for (var e : l) {
            l2.add(e);
        }

        for (var e : other) {
            l2.add(e);
        }

        return l2;
    }

    @Override
    public Set<T> intersection(Set<? extends T> other) {
        ArrayListSet<T> l2 = new ArrayListSet<>();
        for (var e : l) {
            if (other.contains(e)) {
                l2.add(e);
            }
        }
        return l2;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ArrayListSet<?> other = (ArrayListSet<?>) obj;
        if (size() != other.size())
            return false;
        for (var e : l) {
            if (!other.contains(e))
                return false;
        }
        return true;
    }

}

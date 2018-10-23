package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private SimpleArrayList<E> list = new SimpleArrayList<>();

    public void add(E e) {
        if (isUniq(e)) {
            list.add(e);
        }
    }

    public int getSize() {
        return list.getSize();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    private boolean isUniq(E e) {
        for (int i = 0; i < list.getSize(); i++) {
            E element = list.get(i);
            if (element.equals(e)) {
                return false;
            }
        }
        return true;
    }
}

package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<E> implements Iterable<E> {

    private SimpleArrayList<E> list = new SimpleArrayList<>();

    public void add(E e) {
        for (int i = 0; i < list.getSize(); i++) {
            E element = list.get(i);
            if (element == e) {
                return;
            }
        }
        list.add(e);
    }

    public int getSize() {
        return list.getSize();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < list.getSize();
            }

            @Override
            public E next() {
                if (index + 1 >= list.getSize()) {
                    throw new NoSuchElementException();
                }
                return list.get(index++);
            }
        };
    }
}

package Generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] data;
    private int position = 0;
    private int index = 0;

    SimpleArray(int size) {
        this.data = new Object[size];
    }

    void add(T model) {
        if (position < data.length) {
            this.data[position++] = model;

        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    void set(int index, T model) {
        this.data[index] = model;
    }

    void delete(int index) {
        this.data[index] = null;
    }

    @SuppressWarnings("unchecked")
    T get(int index) {
        return (T) this.data[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return index < data.length;
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) data[index++];
            }
        };
    }
}

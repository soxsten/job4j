package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class DynamicContainer<E> implements Iterable<E> {

    private Object[] container;
    private int modCount;
    private int index;

    public DynamicContainer(int containerSize) {
        if (containerSize < 0) {
            containerSize = 10;
        }
        container = new Object[containerSize];
    }

    public DynamicContainer() {
        container = new Object[10];
    }

    public void add(E value) {
        if (isContainerFull()) {
            increaseContainerSize();
        }
        this.container[index++] = value;
        increaseModCount();
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) container[index];
    }

    private int getModCount() {
        return modCount;
    }

    public int getSize() {
        return this.container.length;
    }

    private boolean isContainerFull() {
        return index == container.length;
    }

    private void increaseContainerSize() {
        Object[] newContainer = new Object[(int) (container.length * 1.5)];
        System.arraycopy(this.container, 0, newContainer, 0, container.length);
        this.container = newContainer;
        increaseModCount();
    }

    private void increaseModCount() {
        this.modCount++;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int modCount = getModCount();
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < container.length;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() {
                if (modCount != getModCount()) {
                    throw new ConcurrentModificationException();
                }
                return hasNext() ? (E) container[currentIndex++] : null;
            }
        };
    }
}

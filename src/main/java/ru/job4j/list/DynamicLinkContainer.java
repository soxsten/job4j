package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class DynamicLinkContainer<E> implements Iterable<E> {

    private Node<E> first;
    private int modeCount;
    private int size;

    public void add(E value) {
        Node<E> newLink = new Node<>(value);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
        increaseNodeCount();
    }

    public E get(int index) {
        if (index < 0) {
            return null;
        }
        Node<E> position = first;
        for (int i = 0; i <= index; i++) {
            if (position == null) {
                break;
            }
            if (i == index) {
                return position.date;
            }
            position = position.next;
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    private void increaseNodeCount() {
        modeCount++;
    }

    public int getModCount() {
        return modeCount;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = getModCount();
            private Node<E> pos = first;

            @Override
            public boolean hasNext() {
                if (pos == null) {
                    return false;
                }
                return pos.date != null || pos.next != null;
            }

            @Override
            public E next() {
                if (expectedModCount != getModCount()) {
                    throw new ConcurrentModificationException();
                }
                if (hasNext()) {
                    E current = pos.date;
                    pos = pos.next;
                    return current;
                }
                return null;
            }
        };
    }

    private static class Node<E> {
        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}

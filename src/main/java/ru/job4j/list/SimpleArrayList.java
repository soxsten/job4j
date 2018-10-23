package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ����� SimpleArrayList.
 */
public class SimpleArrayList<E> implements Iterable<E> {

    private int size;
    private Node<E> first;

    /**
     * ����� ��������� � ������ ������ ������.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * ����������� ����� �������� ������� �������� � ������.
     */
    public E delete() {
        E date = this.first.date;
        this.first = first.next;
        if (this.size <= 0) {
            throw new NoSuchElementException();
        }
        this.size--;
        return date;
    }

    /**
     * ����� ��������� �������� �� �������.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result == null ? null : result.date;
    }

    /**
     * ����� ��������� ������� ���������.
     */
    public int getSize() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < getSize();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(index++);
            }
        };
    }

    /**
     * ����� ������������ ��� �������� ������.
     */
    private static class Node<E> {

        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}
